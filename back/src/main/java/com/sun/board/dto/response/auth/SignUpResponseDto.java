package com.sun.board.dto.response.auth;

import com.sun.board.common.response.ResponseCode;
import com.sun.board.common.response.ResponseMessage;
import com.sun.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor		// 모든 멤버변수에 대한 생성자를 만들어주는 것
public class SignUpResponseDto extends ResponseDto {
	
	private SignUpResponseDto (String code, String message) {
		super(code, message);		//부모 생성자를 불러옴
	}

	public static SignUpResponseDto success() {
		SignUpResponseDto result = new SignUpResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		return result;
	}

	public static ResponseDto existedEmail() {
		ResponseDto result = new SignUpResponseDto(ResponseCode.EXISTED_EMAIL, ResponseMessage.EXISTED_EMAIL);
		return result;
	}
	
	public static ResponseDto existedNickname() {
		ResponseDto result = new ResponseDto(ResponseCode.EXISTED_NICKNAME, ResponseMessage.EXISTED_NICKNAME);
		return result;
	}

	public static ResponseDto existedTelNumber() {
		ResponseDto result = new ResponseDto(ResponseCode.EXISTED_TEL_NUMBER, ResponseMessage.EXISTED_TEL_NUMBER);
		return result;
	}
}
