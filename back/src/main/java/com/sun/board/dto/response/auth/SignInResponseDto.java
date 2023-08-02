package com.sun.board.dto.response.auth;

import com.sun.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInResponseDto extends ResponseDto {
	
	private String token;
}
