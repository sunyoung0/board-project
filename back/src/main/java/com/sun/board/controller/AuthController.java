package com.sun.board.controller;

import javax.validation.Valid;

import com.sun.board.dto.request.auth.SignInRequestDto;
import com.sun.board.dto.request.auth.SignUpRequestDto;
import com.sun.board.dto.response.auth.SignInResponseDto;
import com.sun.board.dto.response.auth.SignUpResponseDto;
import com.sun.board.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// controller : 인증 컨트롤러 //
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;

	// API : 회원 가입 메서드 //
	@PostMapping("/sign-up")
	public ResponseEntity<? super SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto requestBody) {
		ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
		return response;
	}

	// API : 로그인 메서드 //
	@PostMapping("/sign-in")
	public ResponseEntity<? super SignInResponseDto> signIn(@RequestBody @Valid SignInRequestDto requestBody) {
		ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
		return response;
	}

}
