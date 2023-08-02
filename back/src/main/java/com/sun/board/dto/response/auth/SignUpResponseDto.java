package com.sun.board.dto.response.auth;

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
		SignUpResponseDto result = new SignUpResponseDto("SU", "Sucess");
		return result;
	}

	public static SignUpResponseDto existedEmail() {
		SignUpResponseDto result = new SignUpResponseDto("EE", "Existed Email");
		return result;
	}


}
