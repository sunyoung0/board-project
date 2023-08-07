package com.sun.board.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sun.board.dto.request.auth.SignInRequestDto;
import com.sun.board.dto.request.auth.SignUpRequestDto;
import com.sun.board.dto.response.ResponseDto;
import com.sun.board.dto.response.auth.SignInResponseDto;
import com.sun.board.dto.response.auth.SignUpResponseDto;
import com.sun.board.entity.UserEntity;
import com.sun.board.repository.UserRepository;
import com.sun.board.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

	private final UserRepository userRepository;

	@Override
	public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'signIn'");
	}

	@Override
	// method : 회원가입 메서드 //
	public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

		String email = dto.getEmail();
		String nickname = dto.getNickname();
		String telNumber = dto.getTelNumber();

		try{
			// description : 이메일 중복 확인 //
			boolean hasEmail = userRepository.existsById(email);
			if (hasEmail) return SignUpResponseDto.existedEmail();

			// description : 닉네임 중복 확인 //
			boolean hasNickname = userRepository.existsByNickname(nickname);
			if (hasNickname) return SignUpResponseDto.existedNickname();

			// description : 전화번호 중복 확인 //
			boolean hasTelNumber = userRepository.existsByTelNumber(telNumber);
			if (hasTelNumber) return SignUpResponseDto.existedTelNumber();
			
			// description : Entity 생성 //
			UserEntity userEntity = new UserEntity(dto);

			// description : 데이터 베이스에 저장 //
			userRepository.save(userEntity);
		} catch(Exception exception){
			// description : 데이터 베이스 에러 //
			exception.printStackTrace();		// 콘솔창에 에러메시지
			return ResponseDto.databaseError();
		}
		
		return SignUpResponseDto.success();
	}
}
