package com.sun.board.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sun.board.common.response.ResponseCode;
import com.sun.board.common.response.ResponseMessage;
import com.sun.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetSearchBoardResponseDto extends ResponseDto {
	
	private List<BoardListResponseDto> boardList;

	private GetSearchBoardResponseDto(String code, String message, List<BoardListResponseDto> boardList) {
		super(code, message);
		this.boardList = boardList;
	}

	public static ResponseEntity<GetSearchBoardResponseDto> success(List<BoardListResponseDto> boardList) {
		GetSearchBoardResponseDto result = new GetSearchBoardResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, boardList);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
