package com.sun.board.dto.response.user;

import com.sun.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchUserNicknameResponseDto extends ResponseDto {
	
	private PatchUserNicknameResponseDto(String code, String message) {
		super(code, message);
	}

	public static PatchUserNicknameResponseDto success() {
		PatchUserNicknameResponseDto result = new PatchUserNicknameResponseDto("SU", "Success");
		return result;
	}

	public static ResponseDto noExistedUser() {
		ResponseDto result = new ResponseDto("NU", "No Existed User");
		return result;
	}

	public static ResponseDto noExistedNickname() {
		ResponseDto result = new ResponseDto("EN", "No Existed Nickname");
		return result;
	}

}
