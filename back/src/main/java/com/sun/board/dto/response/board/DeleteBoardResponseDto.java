package com.sun.board.dto.response.board;

import com.sun.board.common.response.ResponseCode;
import com.sun.board.common.response.ResponseMessage;
import com.sun.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteBoardResponseDto extends ResponseDto {
	
	private DeleteBoardResponseDto(String code, String message){
		super(code, message);
	}

	public static DeleteBoardResponseDto success() {
		DeleteBoardResponseDto result = new DeleteBoardResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		return result;
	}

	public static ResponseDto noExistedUser() {
		ResponseDto result = new ResponseDto(ResponseCode.No_EXISTED_USER,ResponseMessage.No_EXISTED_USER);
		return result;
	}
	
	public static ResponseDto noExistedBoard() {
		ResponseDto result = new ResponseDto(ResponseCode.No_EXISTED_BOARD, ResponseMessage.No_EXISTED_BOARD);
		return result;
	}

	public static ResponseDto noPermission() {
		ResponseDto result = new ResponseDto(ResponseCode.No_EXISTED_PERMISSION, ResponseMessage.No_EXISTED_PERMISSION);
		return result;
	}
}