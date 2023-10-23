package com.princess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.princess.domain.Board;
import com.princess.service.BoardService;
	
@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardService service; 
	
	@RequestMapping("/getBoardList")
	public void getBoardList() {
		
	}
	
	@GetMapping("/getBoard")
	public void getBoard() {
		
	}
	
	@GetMapping("/insertBoard")
	public void insertBoard() {
		
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @RequestParam MultipartFile file) {
		service.insertBoard(board, file);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public void deleteBoard() {
		
	}
	
	@GetMapping("/updateBoard")
	public void updateBoard() {
		
	}
}
