package com.princess.controller;

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
import com.princess.domain.Member;
import com.princess.domain.Search;
import com.princess.service.BoardService;
	
@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardService boardservice; 
	
	@RequestMapping("/getBoardList")
	public void getBoardList (@RequestParam String type, Model model, Search search,
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
		int endPage = Math.min(nowPage +5, boardList.getTotalPages());

		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("boardList1", boardList);
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
