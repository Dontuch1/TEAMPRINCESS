package com.princess.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.princess.domain.Board;
import com.princess.domain.QBoard;
import com.princess.domain.Search;
import com.princess.persistence.BoardRepository;
import com.querydsl.core.BooleanBuilder;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;
	
	@Value("${file.direc}")
	private String path;
	
	public void insertBoard(Board board, MultipartFile file) {
		if (!file.isEmpty()) {
			String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();	
			try {
				file.transferTo(new File(path + filename));
				board.setPhotoPath("/upload/" + filename); 
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			} 
		}
		boardRepo.save(board);
	}
	 
	public Page<Board> getBoardList(Search search) {
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		if(search.getSearchCondition().equals("TITLE")) {
			builder.and(qboard.title.like("%" + search.getSearchKeyword() + "%"));
		} else if(search.getSearchCondition().equals("CONTENT")) {
			builder.and(qboard.title.like("%" + search.getSearchKeyword() + "%"));
		}
		
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "postNum");
		System.out.println(pageable.toString());
		System.out.println(builder.toString());
		return boardRepo.findAll(builder, pageable);
	}

	public void updateBoard(Board board) {
	}

	public void insertBorad(Board board) {
	}

}
