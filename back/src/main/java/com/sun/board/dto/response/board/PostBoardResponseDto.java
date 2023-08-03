package com.sun.board.dto.response.board;

import com.sun.board.common.response.ResponseCode;
import com.sun.board.common.response.ResponseMessage;
import com.sun.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostBoardResponseDto extends ResponseDto {

	private PostBoardResponseDto (String code, String message) {
		super(code, message);
	}

	public static PostBoardResponseDto success() {
		PostBoardResponseDto result = new PostBoardResponseDto(ResponseCode.SUCCESS,ResponseMessage.SUCCESS);
		return result;
	}

	public static ResponseDto noExistedUser() {
		ResponseDto result = new ResponseDto(ResponseCode.No_EXISTED_USER, ResponseMessage.No_EXISTED_USER);
		return result;
	}

}