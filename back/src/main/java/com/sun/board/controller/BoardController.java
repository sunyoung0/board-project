package com.sun.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.board.dto.request.board.PatchBoardRequestDto;
import com.sun.board.dto.request.board.PostBoardRequestDto;
import com.sun.board.dto.request.board.PostCommentRequestDto;
import com.sun.board.dto.request.board.PutFavoriteRequestDto;
import com.sun.board.dto.response.board.DeleteBoardResponseDto;
import com.sun.board.dto.response.board.GetCurrentBoardResponseDto;
import com.sun.board.dto.response.board.PatchBoardResponseDto;
import com.sun.board.dto.response.board.PostBoardResponseDto;
import com.sun.board.dto.response.board.PostCommentResponseDto;
import com.sun.board.dto.response.board.PutFavoriteResponseDto;
import com.sun.board.service.BoardService;

import lombok.RequiredArgsConstructor;

// controller : 게시물 컨트롤러 //
@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	// API : TOP3 게시물 불러오기 메서드 //
	@GetMapping("/top-3")
	public ResponseEntity<?> getTop3List() {
		ResponseEntity<?> response = boardService.getTop3();
		return response;
	}

	// API : 최신 게시물 불러오기 메서드 //
	@GetMapping("/current-board")
	public ResponseEntity<? super GetCurrentBoardResponseDto> getCurrentBoard() {
		ResponseEntity<? super GetCurrentBoardResponseDto> response = boardService.getCurrentBoard();
		return response;
	}

	// API : 게시물 불러오기 메서드 //
	@GetMapping("/{boardNumber}")
	public ResponseEntity<?> getBoard(@PathVariable("boardNumber") Integer boardNumber) {
		ResponseEntity<?> response = boardService.getBoard(boardNumber);
		return response;
	}
	
	// API : 검색 게시물 리스트 불러오기 메서드 //
	@GetMapping("/search/{searchWord}")
	public ResponseEntity<?> getSearchBoardList(@PathVariable("searchWord") String searchWord) {
		ResponseEntity<?> response = boardService.getSearchBoard(searchWord);
		return response;
	}

	// API : 특정 게시물의 좋아요 리스트 불러오기 메서드 //
	@GetMapping("/{boardNumber}/favorite-list")
	public ResponseEntity<?> getFavoriteList(@PathVariable("boardNumber") Integer boardNumber){
		ResponseEntity<?> response = boardService.getFavoriteList(boardNumber);
		return response;
	}

	//  API : 특정 게시물의 댓글 리스트 불러오기 메서드 //
	@GetMapping("/{boardNumber}/comment-list")
	public ResponseEntity<?> getCommentList(@PathVariable("boardNumber") Integer boardNumber) {
		ResponseEntity<?> response = boardService.getCommentList(boardNumber);
		return response;
	}

	// API : 특정 유저의 게시물 리스트 불러오기 메서드 //
	@GetMapping("/user-list/{email}")
	public ResponseEntity<?> getUserList(@PathVariable("email") String email) {
		ResponseEntity<?> response = boardService.getUserList(email);
		return response;
	}

	// API : 게시물 작성 메서드 //
	@PostMapping("")
	public ResponseEntity<? super PostBoardResponseDto> postBoard(@RequestBody @Valid PostBoardRequestDto requestBody){
		ResponseEntity<? super PostBoardResponseDto> response = boardService.postBoard(requestBody);
		return response;
	}

	// API : 댓글 작성 메서드 //
	@PostMapping("/{boardNumber}/comment")
	public ResponseEntity<? super PostCommentResponseDto> postComment(@PathVariable("boardNumber") Integer boardNumber, @RequestBody @Valid PostCommentRequestDto requestBody){
		ResponseEntity<? super PostCommentResponseDto> response = boardService.postComment(boardNumber, requestBody);
		return response;
	}

	// API : 좋아요 기능 메서드 //
	@PutMapping("/{boardNumber}/favorite")
	public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(@PathVariable("boardNumber") Integer boardNumber, @RequestBody @Valid PutFavoriteRequestDto requestBody) {
		ResponseEntity<? super PutFavoriteResponseDto> response = boardService.putFavorite(boardNumber, requestBody);
		return response;
	}

	// API : 게시물 수정 메서드 //
	@PatchMapping("/{boardNumber}")
	public ResponseEntity<? super PatchBoardResponseDto> patchBoard(@PathVariable("boardNumber") Integer boardNumber, @RequestBody @Valid PatchBoardRequestDto requestBody) {
		ResponseEntity<? super PatchBoardResponseDto> response = boardService.patchBoard(boardNumber, requestBody);
		return response;
	}

	// API : 게시물 삭제 메서드 //
	@DeleteMapping("/{boardNumber}/{email}")
	public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(@PathVariable("boardNumber") Integer boardNumber, @PathVariable("email") String email) {
		ResponseEntity<? super DeleteBoardResponseDto> response = boardService.deleteBoard(boardNumber, email);
		return response;
	}
}