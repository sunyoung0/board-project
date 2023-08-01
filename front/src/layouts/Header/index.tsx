import { ChangeEvent, useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';

import { useBoardWriteStore, useUserStore } from 'src/stores';
import { AUTH_PATH, BOARD_DETAIL_PATH, BOARD_UPDATE_PATH, BOARD_WRITE_PATH, MAIN_PATH, SEARCH_PATH, USER_PAGE_PATH } from 'src/constants';
import './style.css';
import PostBoardRequestDto from 'src/interfaces/request/post-board.request.dto';
import PatchBoardRequestDto from 'src/interfaces/request/patch-board.request.dto';
import BoardDetail from 'src/views/Board/Detail';

//					component					//
// description : Header 레이아웃 //
export default function Header() {

	//					state					//
	// description : url 경로 상태 //
	const { pathname } = useLocation(); //pathname을 바로 받아도 됨

	// description : 로그인 유저 정보 상태 //
	const { user, setUser } = useUserStore();
	
	// description : 게시물 작성 데이터 상태 //
	const { boardTitle, boardContent, resetBoard } = useBoardWriteStore();

	// description : 검색 아이콘 클릭 상태 //
	const [searchState, setSearchState] = useState<boolean>(false);

	// description : 로그인 상태 //
	const [login, setLogin] = useState<boolean>(false);

	// description : 검색어 상태 //
	const [search, setSearch] = useState<string>('');
 
	//					function					//
	// description : 페이지 이동을 위한 네비게이트 함수 //
	const navigator = useNavigate();

	// description : search 버튼 출력 여부 //
	const showSearch = !pathname.includes(USER_PAGE_PATH('')) && pathname !== BOARD_WRITE_PATH() && !pathname.includes(BOARD_UPDATE_PATH(''));

	// description : 현재 페이지가 인증 화면인지 여부 //
	const isAuth = pathname === AUTH_PATH;

	// description : 현재 페이지가 마이페이지인지 여부 //
	const isMyPage = pathname.includes(USER_PAGE_PATH(''));

	// description : upload 버튼 출력 여부 //
	const showUpload = pathname === BOARD_WRITE_PATH() || pathname.includes(BOARD_UPDATE_PATH(''));
	
	// description : upload 버튼 활성화 여부 //
	const activeUpload = boardTitle != '' && boardContent != '' ;

	//					event handler					//
	// description : 검색어 변경 이벤트 //
	const onSearchChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
		setSearch(event.target.value);
	}

	// description : 검색 아이콘 버튼 클릭 이벤트 //
	const onSearchOpenButtonClickHandler = () => {
		setSearchState(true);
	}

	// description : 검색 버튼 클릭 이벤트 //
	const onSearchButtonClickHandler = () => {
		if (!search) {
			alert('검색어를 입력해주세요.');
			return;
		}
		navigator(SEARCH_PATH(search));
	}

	// description : 로고 클릭 이벤트 //
	const onLogoClickHandler = () => {
		navigator(MAIN_PATH);
	}

	// description : 로그인 버튼 클릭 이벤트 //
	const onSignInButtonClickHandler = () => {
		setLogin(true);
		navigator(AUTH_PATH);
	}

	// description : 마이페이지 버튼 클릭 이벤트 //
	const onMyPageButtonClickHandler = () => {
		if (!user) return;
		navigator(USER_PAGE_PATH(user.email));
	}

	// description : 로그아웃 버튼 클릭 이벤트 //
	const onSignOutButtonClickHandler = () => {
		setLogin(false);
		setUser(null);
		navigator(MAIN_PATH);
	}

	// description : 업로드 버튼 클릭 이벤트 //
	const onUploadButtonClickHandler = () => {
		if(pathname === BOARD_WRITE_PATH()) {

			const data: PostBoardRequestDto = {
				title:boardTitle,
				content:boardContent,
				imageUrl:''
			}
			axios.post('url', data)
			.then((response) => {
				resetBoard();
				navigator(MAIN_PATH);
			})
			.catch((error) => {

			})
		}
		else {
			// todo : boardNumber 받아오기
			const data: PatchBoardRequestDto = {
				boardNumber: 1,
				title: boardTitle,
				contents: boardContent,
				imageUrl:''
			}
			axios.patch('url',data)
			.then((response) => {
				resetBoard();
				navigator(BOARD_DETAIL_PATH(1));
			})
			.catch((error) => {

			})
		}
	}

	//					effect					//
	// description : 로그인 유저 정보가 바뀔 때마다 실행 //
	useEffect(() => {
		setLogin(user !== null);
	},[user]);

	// description : path url이 바뀔 때마다 실행 //
	useEffect(() => {
		if(!pathname.includes(SEARCH_PATH(''))){
			setSearch('');
			setSearchState(false);
		}
		
	}, [pathname]);

	//					render					//
	return (
		<div id='header'>
			<div className='header-left' onClick={onLogoClickHandler}>
				<div className='header-left-logo-icon'></div>
				<div className='header-left-logo-text'>Hoons Board</div>
			</div>
			<div className='header-right'>
				{  showSearch && (searchState ? (
						<div className='header-search-box'>
						<input className='header-search-input' value={search} onChange={onSearchChangeHandler} />
						<div className='header-icon-box' onClick={onSearchButtonClickHandler}>
							<div className='header-search-icon'></div>
						</div>
					</div>
					) : (
						<div className='header-icon-box' onClick={onSearchOpenButtonClickHandler}>
							<div className='header-search-icon'></div>
						</div>
					))
				}
				{ !isAuth && (
						isMyPage ? (<div className='white-button' onClick={onSignOutButtonClickHandler}> 로그아웃</div>) :
						showUpload && activeUpload ? (<div className='black-button' onClick={onUploadButtonClickHandler}> 업로드 </div>) :
						showUpload && !activeUpload ? (<div className='disable-button'> 업로드 </div>) :
						login ? (<div className='white-button' onClick={onMyPageButtonClickHandler}> 마이페이지</div>) :
						(<div className='black-button' onClick={onSignInButtonClickHandler}> 로그인</div>)
					)
				}
			</div>
		</div>
	)
}
