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
import com.princess.domain.LikeWish;
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
import com.princess.service.ReportService;
	
@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardService boardservice; 
	
	@Autowired
    private ReplyService replyService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReportService reportService;
	
	
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
			@PageableDefault(page = 0, size = 5, sort = "replyNum", direction = Sort.Direction.ASC) Pageable pageable) {
		board = boardservice.getBoard(board);
		Board board1= boardservice.getBoardId(board.getPostNum()); // board에서 postNum을 가져옴
	    System.out.println("boardId : "+board1);
		Reply reply= new Reply();
		System.out.println(reply.toString());
	    
	    Page<Reply> replies = replyService.getBoard(board1, pageable);
	    
	    int nowPage = replies.getPageable().getPageNumber() + 1;
	    int startPage = Math.max(nowPage -4, 1);
	    int endPage = Math.min(nowPage +4, replies.getTotalPages());
	    
	    model.addAttribute("isReportedboard", boardservice.isReported(securityUser.getMember(), board, Type.COMMUNITY));
	    System.out.println("isReportedboard : " +boardservice.isReported(securityUser.getMember(), board, Type.COMMUNITY));
	    
	    // isgreat
	    model.addAttribute("isGreated", boardservice.isGreated(securityUser.getUsername(), board, Type.COMMUNITY));
	    System.out.println("isGreated : " +boardservice.isGreated(securityUser.getUsername(), board, Type.COMMUNITY));
	    
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
		String newCon = report.getRptCon();
		System.out.println(report.getRptCon());
		report.setPostNo(Long.valueOf(postNum));
		System.out.println(report.getPostNo());
		if(reportService.getReport(report)==null) {
			member.setId(id);
			report.setRptId(member);
			report.setSubmit(Display.H);
			Product product = new Product();
			product.setPNo(board.getPostNum());
			report.setPostNo(product.getPNo());
			productService.insertReport(report);
			return "redirect:getBoard?postNum="+postNum;
		} else {
			String rptCon = report.getRptCon();
			rptCon = rptCon + "\n" + newCon;
			System.out.println(rptCon);
			productService.updateReport(report);
			return "redirect:getBoard?postNum="+postNum;	
			
		}
		
		
	}
	
	/*
	 * @GetMapping("reportBoard")
	public String reportBoard(Report report,@RequestParam String postNum ,@RequestParam String id, Board board) {
		Member member = new Member();
		System.out.println("postNum"+postNum);
		System.out.println("id"+id);
		Product product = new Product();
		product.setPNo(board.getPostNum());
		
		
		String newCon = report.getRptCon();
		
		System.out.println("여기까지");
		report.setPostNo(Long.valueOf(postNum));
		report = reportservice.getReport(report);
		
		if(report.getPostNo()==Long.valueOf(postNum)) {
			String rptCon = report.getRptCon();
			rptCon = rptCon + "\n" + newCon;
			productService.updateReport(report);
			return "redirect:getBoard?postNum="+postNum;
		} else {
			report = new Report();
			member.setId(id);
			report.setRptId(member);
			report.setSubmit(Display.H);
			report.setPostNo(product.getPNo());
			productService.insertReport(report);
			return "redirect:getBoard?postNum="+postNum;
		}
		
	}
	 * */
	
	@GetMapping("/updateGreat")
	public String updateGreat(Board board, @AuthenticationPrincipal SecurityUser securityUser) {
		LikeWish likeWish = new LikeWish();
		Member member = new Member();
		member.setId(securityUser.getUsername());
		likeWish.setLikeId(member);
		
		Board board1 = boardservice.getGreat(board);
		
		System.out.println("update cont : " + boardservice.isReported(securityUser.getMember(), board, Type.COMMUNITY));
		if(!boardservice.isGreated(securityUser.getUsername(), board, Type.COMMUNITY)) {
			likeWish.setPNo(board.getPostNum());
			Long greatNum = board1.getGreat() + Long.valueOf("1");
			System.out.println("final great : "+greatNum);
			likeWish.setType(Type.COMMUNITY);
			boardservice.insertGreat(likeWish, greatNum, board1);
		} else {
			Long greatNum = board1.getGreat() - Long.valueOf("1");
			boardservice.deleteGreat(board1, Type.COMMUNITY, member, greatNum);
		}
		return "redirect:getBoard?postNum=" + board.getPostNum();
	}
	
	@GetMapping("/updateBoard")
	public void updateBoard() {
		
	}
	
}
