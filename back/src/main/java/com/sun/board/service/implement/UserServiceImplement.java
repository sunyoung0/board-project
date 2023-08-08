package com.sun.board.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sun.board.dto.request.user.PatchUserNicknameRequestDto;
import com.sun.board.dto.request.user.PatchUserProfileRequestDto;
import com.sun.board.dto.response.ResponseDto;
import com.sun.board.dto.response.user.PatchUserNicknameResponseDto;
import com.sun.board.dto.response.user.PatchUserProfileResponseDto;
import com.sun.board.entity.UserEntity;
import com.sun.board.repository.UserRepository;
import com.sun.board.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

	private final UserRepository userRepository;

	@Override
	public ResponseEntity<?> getUser(String email) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getUser'");
	}

	@Override
	public ResponseEntity<?> getSignInUser() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getSignInUser'");
	}

	@Override
	public ResponseEntity<? super PatchUserNicknameResponseDto> patchUserNickname(String email, PatchUserNicknameRequestDto dto) {

		String nickname = dto.getNickname();

		try{

			// description : 존재하는 유저인지 확인 //
			UserEntity userEntity = userRepository.findByEmail(email);
			if (userEntity == null) return PatchUserNicknameResponseDto.noExistedUser();

			// description : 중복되는 닉네임인지 확인 //
			boolean hasNickname = userRepository.existsByNickname(nickname);
			if (hasNickname) return PatchUserNicknameResponseDto.existedNickname();

			// description : 수정 //
			userEntity.setNickname(nickname);

			// description : 데이터베이스에 저장 //
			userRepository.save(userEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return PatchUserNicknameResponseDto.success();
	}

	@Override
	public ResponseEntity<? super PatchUserProfileResponseDto> patchUserProfile(String email, PatchUserProfileRequestDto dto) {
		
		String profileImage = dto.getProfileImage();

		try {

			// description : 존재하는 유저인지 확인 //
			UserEntity userEntity = userRepository.findByEmail(email);
			if (userEntity == null) return PatchUserProfileResponseDto.NoExistedUser();

			// description : 수정 //
			userEntity.setProfileImageUrl(profileImage);

			// description : 데이터베이스에 저장 //
			userRepository.save(userEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return PatchUserProfileResponseDto.success();
	
	}
	
}
