package com.sun.board.dto.response.user;

import com.sun.board.common.response.ResponseCode;
import com.sun.board.common.response.ResponseMessage;
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
		PatchUserNicknameResponseDto result = new PatchUserNicknameResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		return result;
	}

	public static ResponseDto noExistedUser() {
		ResponseDto result = new ResponseDto(ResponseCode.No_EXISTED_USER, ResponseMessage.No_EXISTED_USER);
		return result;
	}

	public static ResponseDto existedNickname() {
		ResponseDto result = new ResponseDto(ResponseCode.EXISTED_NICKNAME, ResponseMessage.EXISTED_NICKNAME);
		return result;
	}
}