package com.sun.board.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.board.common.response.CustomResponse;
import com.sun.board.dto.request.user.PatchUserNicknameRequestDto;
import com.sun.board.dto.request.user.PatchUserProfileRequestDto;
import com.sun.board.dto.response.user.PatchUserNicknameResponseDto;
import com.sun.board.dto.response.user.PatchUserProfileResponseDto;

// controller : 유저 컨트롤러 //
@RestController
@RequestMapping("api/v1/user")
public class UserController {
	
	// API : 유저 정보 불러오기 메서드 //
	@GetMapping("/{email}")
	public ResponseEntity<?> getUser(
		@PathVariable("email") String email
	) {
		return CustomResponse.serviceUnavailable;
	}

	// API : 로그인 유저 정보 불러오기 메서드 //
	@GetMapping("")
	public ResponseEntity<?> getSignInUser() {
		return CustomResponse.serviceUnavailable;
	}

	// API : 유저 닉네임 수정 메서드 //
	@PatchMapping("{email}/nickname")
	public ResponseEntity<? super PatchUserNicknameResponseDto> patchUserNickname(@PathVariable("email") String email, @RequestBody @Valid PatchUserNicknameRequestDto requestBody) {
		PatchUserNicknameResponseDto responseBody = PatchUserNicknameResponseDto.success();
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	}

	// API : 유저 프로필 이미지 수정 메서드 //
	@PatchMapping("/{email}/profile")
	public ResponseEntity<? super PatchUserProfileResponseDto> patchUserProfile(@PathVariable("email") String email, @RequestBody @Valid PatchUserProfileRequestDto requestBody) {
		PatchUserProfileResponseDto responseBody = PatchUserProfileResponseDto.success();
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	}
}