 package com.princess.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.princess.domain.Board;
import com.princess.domain.CheckCondition.Display;
import com.princess.domain.Search;
import com.princess.service.BoardService;
	
@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardService boardservice; 
	
	@RequestMapping("/getBoardList")
	public String getBoardList (@RequestParam String type, Model model, Search search,
			@PageableDefault(page = 0, size = 10, sort = "postNum", direction = Sort.Direction.DESC) Pageable pageable) {
		System.out.println("getBoardList1 : " + type);
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
	public String getBoard(Model model, Board board) {
		model.addAttribute("board", boardservice.getBoard(board));

		return "board/getBoard";
	}
	
	@GetMapping("/insertBoard")
	public void insertBoard() {
		
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
	
	@GetMapping("/deleteBoard")
	public void deleteBoard() {
		
	}
	
	@GetMapping("/updateBoard")
	public void updateBoard() {
		
	}
}
