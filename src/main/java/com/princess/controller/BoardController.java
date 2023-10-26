package com.princess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.princess.domain.Board;
import com.princess.domain.Search;
import com.princess.service.BoardService;
	
@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardService boardservice; 
	
	@RequestMapping("/getBoardList")
	public String getBoardList (Model model, Search search) {
		if(search.getSearchCondition() == null) {
			search.setSearchCondition("TITLE");
		} else if(search.getSearchKeyword() == null) {
			search.setSearchKeyword(" ");
		}
		Page<Board> boardList = boardservice.getBoardList(search);
		System.out.println("boardList:"+boardList);
		System.out.println("hi");
		model.addAttribute("boardList", boardList);
		return "board/getBoardList";
	}
	
	@GetMapping("/getBoard")
	public void getBoard() {
		
	}
	
	@GetMapping("/insertBoard")
	public void insertBoard() {
		
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @RequestParam MultipartFile file) {
		boardservice.insertBoard(board, file);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public void deleteBoard() {
		
	}
	
	@GetMapping("/updateBoard")
	public void updateBoard() {
		
	}
}
