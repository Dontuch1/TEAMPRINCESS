 package com.princess.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.princess.config.SecurityUser;
import com.princess.domain.Board;
import com.princess.domain.CheckCondition.Display;
import com.princess.domain.CheckCondition.Type;
import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.Reply;
import com.princess.domain.Report;
import com.princess.domain.Search;
import com.princess.service.BoardService;
import com.princess.service.ProductService;
import com.princess.service.ReplyService;
	
@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardService boardservice; 
	
	@Autowired
    private ReplyService replyService;
	
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping("/getBoardList")
	public String getBoardList (@RequestParam String type, Model model, Search search,
			@PageableDefault(page = 0, size = 10, sort = "postNum", direction = Sort.Direction.DESC) Pageable pageable) {
		
		if(search.getSearchCondition()==null) {
			search.setSearchCondition("TITLE");
		} 
		if (search.getSearchKeyword()==null) {
			search.setSearchKeyword("");
		}
		
		Page<Board> boardList = boardservice.getBoardList(type, search, pageable);
		
		int nowPage = boardList.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage -4, 1);
		int endPage = Math.min(nowPage +4, boardList.getTotalPages());

		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("boardList1", boardList);
		model.addAttribute("type", type);
		System.out.println("getBoardList"+type);
		return "board/getBoardList";
	}	
	
	@GetMapping("/getBoard")
	public String getBoard(Model model, Board board, @AuthenticationPrincipal SecurityUser securityUser,
			@PageableDefault(page = 0, size = 5, sort = "replyNum", direction = Sort.Direction.DESC) Pageable pageable) {
		board = boardservice.getBoard(board);
		Board board1= boardservice.getBoardId(board.getPostNum()); // board에서 postNum을 가져옴
	    System.out.println("boardId : "+board1);
		Reply reply= new Reply();
		System.out.println(reply.toString());
	    
	    Page<Reply> replies = replyService.findByPostNum(board1, pageable);
	    
	    int nowPage = replies.getPageable().getPageNumber() + 1;
	    int startPage = Math.max(nowPage -4, 1);
	    int endPage = Math.min(nowPage +4, replies.getTotalPages());
	    
	    // isgreat
	    model.addAttribute("isGreated", boardservice.isGreated(securityUser.getUsername(), board, Type.COMMUNITY));
	    System.out.println("isGreated : " +boardservice.isGreated(securityUser.getUsername(), board, Type.COMMUNITY));
	    
	    model.addAttribute("isReportedboard", boardservice.isReported(securityUser.getMember(), board, Type.COMMUNITY));
	    System.out.println("isReportedboard : " +boardservice.isReported(securityUser.getMember(), board, Type.COMMUNITY));
	    
	    model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
	    model.addAttribute("replies1", replies);
		model.addAttribute("board", board);

		return "board/getBoard";
	}
	
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @RequestParam MultipartFile file) {
		board.setRegdate(new Date());
		board.setDisplay(Display.Y);
		board.setGreat((long) 0);
		String type=board.getCmCategory().toString();
		boardservice.insertBoard(board, file);
		if(type=="NOTICE") {
			return "redirect:getBoardList?type=notice";
		} else if(type=="LOST") {
			return "redirect:getBoardList?type=lost";
		} else if(type=="FOOD") {
			return "redirect:getBoardList?type=food";
		} else if(type=="TMI") {
			return "redirect:getBoardList?type=tmi";
		} else if(type=="QNA") {
			return "redirect:getBoardList?type=qna";
		} else {
			return "redirect:getBoardList?type=meet";
		}
	}
	
	@GetMapping("/insertBoard")
	public void insertBoard(Model model) {
		model.addAttribute("Board", null);
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		String type = board.getCmCategory().toString();
		boardservice.deleteBoard(board);
		System.out.println("type : "+type);
		if(type=="NOTICE") {
			return "redirect:getBoardList?type=notice";
		} else if(type=="LOST") {
			return "redirect:getBoardList?type=lost";
		} else if(type=="FOOD") {
			return "redirect:getBoardList?type=food";
		} else if(type=="TMI") {
			return "redirect:getBoardList?type=tmi";
		} else if(type=="QNA") {
			return "redirect:getBoardList?type=qna";
		} else {
			return "redirect:getBoardList?type=meet";
		}
	}
	
	@GetMapping("reportBoard")
	public String reportBoard(Report report,@RequestParam String postNum ,@RequestParam String id, Board board) {
		Member member = new Member();
		System.out.println("postNum"+postNum);
		System.out.println("id"+id);
		member.setId(id);
		report.setRptId(member);
		report.setSubmit(Display.H);
		Product product = new Product();
		product.setPNo(board.getPostNum());
		report.setPostNo(product.getPNo());
		productService.insertReport(report);
		return "redirect:getBoard?postNum="+postNum;
	}
	
	@GetMapping("/updateBoard")
	public void updateBoard() {
		
	}
	
}
