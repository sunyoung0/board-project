package com.sun.board.dto.response.user;

import com.sun.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchUserProfileResponseDto extends ResponseDto {
	
	private PatchUserProfileResponseDto(String code, String message) {
		super(code, message);
	}

	public static PatchUserProfileResponseDto success() {
		PatchUserProfileResponseDto result = new PatchUserProfileResponseDto("SU", "Success");
		return result;
	}

	public static ResponseDto NoExistedUser() {
		ResponseDto result = new ResponseDto("NU", "No Existed User");
		return result;
	}

}