package com.sun.board.dto.response.board;

import com.sun.board.common.response.ResponseCode;
import com.sun.board.common.response.ResponseMessage;
import com.sun.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PutFavoriteResponseDto extends ResponseDto{
	
	private PutFavoriteResponseDto(String code, String message) {
		super(code, message);
	}

	public static PutFavoriteResponseDto success() {
		PutFavoriteResponseDto result = new PutFavoriteResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		return result;
	}

	public static ResponseDto noExistedUser() {
		ResponseDto result = new ResponseDto(ResponseCode.No_EXISTED_USER, ResponseMessage.No_EXISTED_USER);
		return result;
	}

	public static ResponseDto noExistedBoard() {
		ResponseDto result = new ResponseDto(ResponseCode.No_EXISTED_BOARD, ResponseMessage.No_EXISTED_BOARD);
		return result;
	}
}