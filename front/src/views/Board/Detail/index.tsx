import { ChangeEvent, useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

import CommentListItem from 'src/components/CommentListItem';
import Pagination from 'src/components/Pagination';
import { BOARD_UPDATE_PATH, COUNT_BY_PAGE_COMMENT, MAIN_PATH, USER_PAGE_PATH } from 'src/constants';
import { usePagination } from 'src/hooks';
import { BoardDetailResponseDto, CommentListResponseDto, FavoriteListResponseDto } from 'src/interfaces/response';
import { boardDetailMock, commentListMock, favoriteListMock } from 'src/mocks';

import { useUserStore } from 'src/stores';

import './style.css';
import axios from 'axios';

// 7/25 강의 영상도 보기 앞 2시간만
//          component          //
// description: 게시물 상세 화면 //
export default function BoardDetail() {
  //          state          //
  // description: 게시물 번호 상태 //
  const { boardNumber } = useParams();

  // description : 로그인 유저 정보 상태 //
  const { user } = useUserStore();

  // description : 페이지네이션 관련 상태 및 함수 //
  const { totalPage, currentPage, currentSection, onPageClickHandler, onPreviousClickHandler, onNextClickHandler, changeSection } = usePagination();

  // description: 게시물 정보 상태 //
  const [board, setBoard] = useState<BoardDetailResponseDto | null>(null);

  // description: 게시물 좋아요 회원 리스트 상태 //
  const [favoriteList, setFavoriteList] = useState<FavoriteListResponseDto[]>([]);

  // description: 댓글 리스트 상태 //
  const [commentList, setCommentList] = useState<CommentListResponseDto[]>([]);

  // description : 현재 페이지에서 보여줄 댓글 리스트 상태 //
  const [pageCommentList, setPageCommentList] = useState<CommentListResponseDto[]>([]);

  // description: 좋아요 리스트 컴포넌트 출력 상태 //
  const [showFavoriteList, setShowFavoriteList] = useState<boolean>(false);

  //description : 댓글 리스트 컴포넌트 출력 상태 //
  const [showCommentList, setShowCommentList] = useState<boolean>(false);

  //          function          //
  // description : 페이지 이동을 위한 네비게이트 함수 //
  const navigator = useNavigate();

  // description  현재 페이지의 댓글 리스트 분류 함수 //
  const getPageCommentList = () => {
    const lastIndex = commentListMock.length > COUNT_BY_PAGE_COMMENT * currentPage ?
      COUNT_BY_PAGE_COMMENT * currentPage : commentListMock.length;
    
    const startIndex = COUNT_BY_PAGE_COMMENT * (currentPage -1);

    const pageCommentList = commentListMock.slice(startIndex, lastIndex);

    setPageCommentList(pageCommentList);
  }

  //          event handler          //

  //          component          //
  // description: 실제 게시물 컴포넌트 //
  const Board = () => {
    //          state          //
    // description: more 버튼 출력 상태 //
    const [viewMore, setViewMore] = useState<boolean>(true);

    // description: more 버튼 클릭 상태 //
    const [openMore, setOpenMore] = useState<boolean>(false);

    // description: favorite 상태 //
    const [favorite, setFavorite] = useState<boolean>(false);

    //          function          //

    //          event handler          //
    // description : 작성자 닉네임 클릭 이벤트 //
    const onWriterNicknameClickHandler = () => {
      if (!board) return;
      navigator(USER_PAGE_PATH(board?.writerEmail));
    }

    // description: more 버튼 클릭 이벤트 //
    const onMoreButtonClickHandler = () => {
      setOpenMore(!openMore);
    }

    // description : 수정 버튼 클릭 이벤트 //
    const onUpdateButtonClickHandler = () => {
      if (!board) return;
      navigator(BOARD_UPDATE_PATH(board.boardNumber));
    }

    // description : 삭제 버튼 클릭 이벤트 //
    const onDeleteButtonClickHandler = () => {
     
      axios.delete('url')
      .then((response) => {
        navigator(MAIN_PATH);
      })
      .catch((error) => {

      });
    }

    // description: 좋아요 버튼 클릭 이벤트 //
    const onFavoriteButtonClickHandler = () => {
      setFavorite(!favorite);
    }

    // description: 좋아요 리스트 펼치기 클릭 이벤트 //
    const onShowFavoriteListButtonClickHandler = () => {
      setShowFavoriteList(!showFavoriteList);
    }

    // description : 댓글 리스트 펼치기 클릭 이벤트 //
    const onShowCommentListButtonClickHandler = () => {
      setShowCommentList(!showCommentList);
    }

    //          effect          //
    // description : 좋아요 리스트가 변경되면 실행 //
     useEffect(() => {
      const favorited = favoriteList.findIndex((item) => item.favoriteUserEmail === user?.email);
      setFavorite(favorited !== -1);
    },[favoriteList]);

    // description : 게시물 번호 혹은 로그인 유저 정보가 변경되면 실행 //
    useEffect(() => {
      setViewMore(user?.email === board?.writerEmail );
      const favorited = favoriteList.findIndex((item) => item.favoriteUserEmail === user?.email);
      setFavorite(favorited !== -1);
    },[boardNumber, user]);

    //          render          //
    return (
      <div className='board-detail-container'>
        <div className='board-detail-top'>
          <div className='board-detail-title-container'>
            <div className='board-detail-title'>{board?.boardTitle}</div>
          </div>
          <div className='board-detail-meta-container'>
            <div className='board-detail-meta-left'>
              <div className='board-detail-writer-profile-image' style={{ backgroundImage: `url(${board?.writerProfileImage})` }}></div>
              <div className='board-detail-writer-nickname' onClick={onWriterNicknameClickHandler}>{board?.writerNickName}</div>
              <div className='board-detail-write-date'>{'\|'}</div>
              <div className='board-detail-write-date'>{board?.writeDate}</div>
            </div>
            <div className='board-detail-meta-right'>
              {openMore && (
                <div className='more-button-group'>
                  <div className='more-button' onClick={onUpdateButtonClickHandler}>수정</div>
                  <div className='divider'></div>
                  <div className='more-button-red' onClick={onDeleteButtonClickHandler}>삭제</div>
                </div>
              )}
              {viewMore && (
                <div className='board-detail-more-button' onClick={onMoreButtonClickHandler}>
                  <div className='more-icon'></div>
                </div>
              )}
            </div>
          </div>
        </div>
        <div className='divider'></div>
        <div className='board-detail-middle'>
          <div className='board-detail-content'>{board?.boardContent}</div>
          <div className='board-detail-image-box'>
            <img className='board-detail-image' src={board?.boardImage} />
          </div>
        </div>
        <div className='board-detail-bottom'>
          <div className='board-detail-bottom-item'>
            <div className='board-detail-bottom-button' onClick={onFavoriteButtonClickHandler}>
              { favorite ? (<div className='favorite-fill-icon'></div>) : (<div className='favorite-icon'></div>) }
            </div>
            <div className='board-detail-bottom-text'>{`좋아요 ${favoriteList.length}`}</div>
            <div className='board-detail-bottom-button' onClick={onShowFavoriteListButtonClickHandler}>
              { showFavoriteList ? (<div className='up-icon'></div>) : (<div className='down-icon'></div>) }
            </div>
          </div>
          <div className='board-detail-bottom-item'>
            <div className='board-detail-bottom-button'>
              <div className='comment-icon'></div>
            </div>
            <div className='board-detail-bottom-text'>{`댓글 ${commentList.length}`}</div>
            <div className='board-detail-bottom-button' onClick={onShowCommentListButtonClickHandler}>
              { showCommentList ? (
                <div className='up-icon'></div>
              ) : (
                <div className='down-icon'></div>
              )}
              
            </div>
          </div>
        </div>
      </div>
    );
  }

  //          component         //
  // description: 좋아요 리스트 컴포넌트 //
  const FavoriteList = () => {

    //          state          //
    
    //          function          //
    
    //          event handler         //
   
    //          component         //
   
    //          effect          //

    //          render          //
    return (
      <div className='favorite-list-box'>
        <div className='favorite-list-title'>좋아요 <span className='favorite-list-title-emphasis'>{favoriteList.length}</span></div>
        <div className='favorite-list-container'>
          {favoriteList.map((item) => (
            <div className='favorite-list-item'>
              <div className='favorite-user-profile' style={{ backgroundImage: `url(${item.favoriteUserProfileImage})` }}></div>
              <div className='favorite-user-nickname'>{item.favoriteUserNickname}</div>
            </div>
          ))}
        </div>
      </div>
    );
  }

  //          component          //
  // description: 댓글 컴포넌트 //
  const Comments = () => {
    //          state         //
    // description : 사용자 댓글 입력 상태 //
    const [comment, setComment] = useState<string>('');

    //          function          //

    //          event handler         //
    // description : 사용자 댓글 입력 변경 이벤트 //
    const onCommentChangeHandler = (event:ChangeEvent<HTMLTextAreaElement>) => {
      setComment(event.target.value);
    }

    //          component       //

    //          effect          //

    //          render          //
    return (
      <div className='comment-list-box'>
				<div className='comment-list-top'>
					<div className='comment-list-title'> 댓글 <span className='comment-list-title-emphasis'> {commentList.length}</span></div>
					<div className='comment-list-container'>
						{ pageCommentList.map((item) => (<CommentListItem item={ item } />))}
					</div>
				</div>
				<div className='divider'></div>
        { commentList.length !== 0 && (
          <Pagination totalPage={totalPage} currentPage={currentPage} onPageClickHandler={onPageClickHandler} onPreviousClickHandler={onPreviousClickHandler} onNextClickHandler={onNextClickHandler}/>
        )}
				<div className='comment-box'>
					<textarea className='comment-textarea' placeholder='댓글을 작성해주세요.' rows={3} value={comment} onChange={onCommentChangeHandler}></textarea>
					<div className='comment-button-box'>
            { comment ? (<div className='black-button'>댓글달기</div>) : (<div className='disable-button'> 댓글달기</div>)}
					</div>
				</div>
			</div>
    );
  }

  //          effect          //
  // description: 게시물 번호가 바뀔 때마다 새로운 정보 받아오기 //
  useEffect(() => {
    setBoard(boardDetailMock);
    setFavoriteList(favoriteListMock);
    setCommentList(commentListMock);

    getPageCommentList();

    changeSection(commentListMock.length, COUNT_BY_PAGE_COMMENT);
  }, [boardNumber]);

  // description : 현재 페이지가 바뀔때마다 검색 게시물 분류 //
  useEffect(() => {
    getPageCommentList();
  }, [currentPage])

  // description : 현재 페이지가 바뀔때마다 페이지 리스트 변경 //
  useEffect(() => {
    changeSection(commentListMock.length, COUNT_BY_PAGE_COMMENT);
  },[currentSection])

  //          render          //
  return (
    <div id='board-detail-wrapper'>
      <Board />
      {showFavoriteList && (<FavoriteList />)}
      {showCommentList && (<Comments />)}
    </div>
  )
}