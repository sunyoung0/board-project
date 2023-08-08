package com.sun.board.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sun.board.dto.request.board.PatchBoardRequestDto;
import com.sun.board.dto.request.board.PostBoardRequestDto;
import com.sun.board.dto.request.board.PostCommentRequestDto;
import com.sun.board.dto.request.board.PutFavoriteRequestDto;
import com.sun.board.dto.response.ResponseDto;
import com.sun.board.dto.response.board.DeleteBoardResponseDto;
import com.sun.board.dto.response.board.GetCurrentBoardResponseDto;
import com.sun.board.dto.response.board.PatchBoardResponseDto;
import com.sun.board.dto.response.board.PostBoardResponseDto;
import com.sun.board.dto.response.board.PostCommentResponseDto;
import com.sun.board.dto.response.board.PutFavoriteResponseDto;
import com.sun.board.dto.response.board.BoardListResponseDto;

import com.sun.board.entity.BoardEntity;
import com.sun.board.entity.CommentEntity;
import com.sun.board.entity.FavoriteEntity;
import com.sun.board.repository.BoardRepository;
import com.sun.board.repository.CommentRepository;
import com.sun.board.repository.FavoriteRepository;
import com.sun.board.repository.UserRepository;
import com.sun.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	private final CommentRepository commentRepository;
	private final FavoriteRepository favoriteRepository;

	@Override
	// method : 최신 게시물 불러오기 //
	public ResponseEntity<? super GetCurrentBoardResponseDto> getTop3() {

		
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getCurrentBoard'");

	}

	@Override
	public ResponseEntity<? super GetCurrentBoardResponseDto> getCurrentBoard() {
		List<BoardListResponseDto> boardList = null;

		try{

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return GetCurrentBoardResponseDto.success(boardList);
	}

	@Override
	public ResponseEntity<?> getBoard(Integer boardNumber) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getBoard'");
	}

	@Override
	public ResponseEntity<?> getSearchBoard(String searchWord) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getSearchBoard'");
	}

	@Override
	public ResponseEntity<?> getFavoriteList(Integer boardNumber) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getFavoriteList'");
	}

	@Override
	public ResponseEntity<?> getCommentList(Integer boardNumber) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getCommentList'");
	}

	@Override
	public ResponseEntity<?> getUserList(String email) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getUserList'");
	}

	@Override
	// method : 게시물 작성 //
	public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto) {

		String writerEmail = dto.getWriterEmail();

		try{
			// description : 작성자 이메일이 존재하는 이메일인지 확인 //
			boolean hasUser = userRepository.existsByEmail(writerEmail);
			if (!hasUser) return PostBoardResponseDto.noExistedUser();

			// description : entity 생성 //
			BoardEntity boardEntity = new BoardEntity(dto);

			// description : 데이터베이스에 저장 //
			boardRepository.save(boardEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return PostBoardResponseDto.success();
	}

	@Override
	// method : 댓글 작성 //
	public ResponseEntity<? super PostCommentResponseDto> postComment(Integer boardNumber, PostCommentRequestDto dto) {
		String userEmail = dto.getUserEmail();

    try {
      // description: boardNumber가 null 인지 확인 //
      // todo: (추후 controller로 이동) //
      if (boardNumber == null) return PostCommentResponseDto.noExistedBoard();

      // description: 존재하는 회원인지 확인 //
      boolean hasUser = userRepository.existsByEmail(userEmail);
      if (!hasUser) return PostCommentResponseDto.noExistedUser();

      // description: 존재하는 게시물인지 확인 //
      boolean hasBoard = boardRepository.existsByBoardNumber(boardNumber);
      if (!hasBoard) return PostCommentResponseDto.noExistedBoard();

      // description: entity 생성 //
      CommentEntity commentEntity = new CommentEntity(boardNumber, dto);

      // description: 데이터베이스 저장 //
      commentRepository.save(commentEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return PostCommentResponseDto.success();

	}

	@Override
	// method : 좋아요 누르기 //
	public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, PutFavoriteRequestDto dto) {

		String userEmail = dto.getUserEmail();
		try {
			// description: boardNumber가 null 인지 확인 //
			if (boardNumber == null) return PutFavoriteResponseDto.noExistedBoard();

			// description : 존재하는 회원인지 확인 //
			boolean hasUser = userRepository.existsByEmail(userEmail);
			if (!hasUser) return PutFavoriteResponseDto.noExistedUser();

			// description : 존재하는 게시물인지 확인 //
			boolean hasBoard = boardRepository.existsByBoardNumber(boardNumber);
			if (!hasBoard) return PutFavoriteResponseDto.noExistedBoard();

			// description : 해당 유저가 해당 게시물에 좋아요 했는지 확인 //
			boolean isFavorite = favoriteRepository.existsByUserEmailAndBoardNumber(userEmail, boardNumber);

			// description : entity 생성 //
			FavoriteEntity favoriteEntity = new FavoriteEntity(boardNumber, userEmail);

			// description : 이미 좋아요 했을 때 //
			if (isFavorite) favoriteRepository.delete(favoriteEntity);
			// description : 아직 좋아요 하지 않았을 때 //
			else favoriteRepository.save(favoriteEntity);
			
		} catch (Exception exception){
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return PutFavoriteResponseDto.success();
	
	}

	@Override
	public ResponseEntity<? super PatchBoardResponseDto> patchBoard(Integer boardNumber, PatchBoardRequestDto dto) {

		String userEmail = dto.getUserEmail();
		try{

			// TODO : 추후 controller로 이동 //
			if (boardNumber == null) return PatchBoardResponseDto.noExistedBoard();

			// description : 존재하는 유저인지 확인 //
			boolean hasUser = userRepository.existsByEmail(userEmail);
			if (!hasUser) return PatchBoardResponseDto.noExistedUser();

			// description : 존재하는 게시물인지 확인 //
			BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
			if ( boardEntity == null) return PatchBoardResponseDto.noExistedBoard();

			// description : 작성자 이메일과 입력받은 이메일이 같은 지 //
			boolean equalWriter = boardEntity.getWriterEmail().equals(userEmail);
			if (!equalWriter) return PatchBoardResponseDto.noPermission();

			// description : 수정 //
			boardEntity.patch(dto);

			// description : 데이터 베이스에 저장 //
			boardRepository.save(boardEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}
		return PatchBoardResponseDto.success();

	}

	@Override
	// method : 게시물 삭제 //
	public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email) {
		
		try{
			// TODO : 추후 컨트롤러로 이동 //
			if (boardNumber == null) return DeleteBoardResponseDto.noExistedBoard();

			// TODO : 추후 컨트롤러로 이동 //
			if (email == null) return DeleteBoardResponseDto.noExistedUser();

			// description : 존재하는 유저인지 확인 //
			boolean hasUser = userRepository.existsByEmail(email);
			if (!hasUser) return DeleteBoardResponseDto.noExistedUser();

			// description : 존재하는 게시물인지 확인 //
			BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
			if (boardEntity == null) return DeleteBoardResponseDto.noExistedBoard();

			// description : 게시물 작성자 이메일과 입력 받은 이메일이 같은 지 확인 //
			boolean equalWriter = boardEntity.getWriterEmail().equals(email);
			if (!equalWriter) return DeleteBoardResponseDto.noPermission();

			// description : 댓글 데이터 삭제 //
			commentRepository.deleteByBoardNumber(boardNumber);

			// description : 좋아요 데이터 삭제 //
			favoriteRepository.deleteByBoardNumber(boardNumber);

			// description : 게시물 삭제 //
			boardRepository.delete(boardEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return DeleteBoardResponseDto.success();

	}
	
}
