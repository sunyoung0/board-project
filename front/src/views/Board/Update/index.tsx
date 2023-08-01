import { ChangeEvent, useEffect, useRef, useState } from 'react'
import { useParams } from 'react-router-dom';
import { useBoardWriteStore } from 'src/stores';
import { boardUpdateMock } from 'src/mocks';
import './style.css';

//					component					//
// description : 게시물 수정 화면 //
export default function BoardUpdate() {

	//					state					//
	// description : textarea 요소에 대한 참조 상태 //
	const textAreaRef = useRef<HTMLTextAreaElement>(null);

	// description : file input 요소에 대한 참조 상태 //
	const fileInputRef = useRef<HTMLInputElement>(null);

	// description : 게시물 번호 상태 //
	const { boardNumber } = useParams();

	// description : 게시물 정보를 저장할 상태 //
	const { boardTitle, boardContent, boardImage, setBoardTitle, setBoardContent, setBoardImage } = useBoardWriteStore();

	// description : 이미지를 저장할 상태 //
	const [boardImageUrl, setBoardImageUrl] = useState<string>('');

	//					function					//

	//					event handler					//
	// description : 제목이 바뀔 시 실행 될 이벤트 //
	const onTitleChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
		setBoardTitle(event.target.value);
	}

	// description : 본문 내용이 바뀔 시 text area 높이 변경 이벤트 //
	const onContentChangeHandler = (event: ChangeEvent<HTMLTextAreaElement>) => {
		setBoardContent(event.target.value);
		if (!textAreaRef.current) return;
		textAreaRef.current.style.height = 'auto'
		textAreaRef.current.style.height = `${textAreaRef.current.scrollHeight}px`;
	}

	// description : 이미지 변경 시 이미지 미리보기 //
	const onImageInputChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
		if(!event.target.files || !event.target.files.length) return;
		const imageUrl = URL.createObjectURL(event.target.files[0]);
		setBoardImageUrl(imageUrl);
		setBoardImage(event.target.files[0]);
	}

	// description : 이미지 업로드 버튼 클릭 이벤트 //
	const onImageUploadButtonClickHandler = () => {
		if(!fileInputRef.current) return;
		fileInputRef.current.click();
	}

	// description : 이미지 닫기 버튼 클릭 이벤트 //
	const onImageCloseButtonClickHandler = () => {
		if(!fileInputRef.current) return;
		fileInputRef.current.value = '';
		setBoardImageUrl('');
	}

	//					component					//

	//					effect					//
	useEffect(() => {
		setBoardTitle(boardUpdateMock.boardTitle);
		setBoardContent(boardUpdateMock.boardContent);
		setBoardImageUrl(boardUpdateMock.boardImage);
	},[boardNumber]);

	//					render					//
	return (
		<div id='board-write-wrapper'>
			<div className='board-write-container'>
				<div className='board-write-title-container'>
					<input className='board-write-title-input' type='text' placeholder='제목을 작성해주세요.' onChange={onTitleChangeHandler} value={boardTitle}/>
				</div>
				<div className='divider'></div>
				<div className='board-write-content-container'>
					<div className='board-write-content-input-box'>
						<textarea ref={textAreaRef} className='board-write-content-textarea' placeholder='본문을 작성해주세요.' onChange={onContentChangeHandler} value={boardContent}></textarea>
					</div>
					<div className='board-write-content-button-box'>
						<div className='image-upload-button' onClick={onImageUploadButtonClickHandler}>
							<div className='image-upload-icon'></div>
						</div>
						<input ref={fileInputRef} type='file' accept='image/*' style={{display: 'none'}} onChange={onImageInputChangeHandler} />
					</div>
				</div>
				{boardImageUrl && (
					<div className='board-write-image-container'>
						<div className='board-write-image'>
							<img className='board-write-img' src={boardImageUrl} />
							<div className='board-write-image-delete-button' onClick={onImageCloseButtonClickHandler}>
								<div className='image-close-icon'></div>
							</div>
						</div>
					</div>
				)}
				
			</div>
		</div>
	)
}