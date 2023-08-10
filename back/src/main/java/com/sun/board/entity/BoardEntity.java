package com.sun.board.entity;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.board.dto.request.board.PatchBoardRequestDto;
import com.sun.board.dto.request.board.PostBoardRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board")
@Table(name="board")
public class BoardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int boardNumber;
	private String title;
	private String contents;
	private String imageUrl;
	private int viewCount;
	private int commentCount;
	private int favoriteCount;
	private String writeDatetime;
	private String writerEmail;

	  public BoardEntity(PostBoardRequestDto dto) {
    Date now = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String writeDatetime = simpleDateFormat.format(now);

    this.title = dto.getTitle();
    this.contents = dto.getContents();
    this.imageUrl = dto.getImageUrl();
    this.writeDatetime = writeDatetime;
    this.writerEmail = dto.getWriterEmail();
  }

	// 수정 시
	public void patch(PatchBoardRequestDto dto) {
		this.title = dto.getTitle();
		this.contents = dto.getContents();
		this.imageUrl = dto.getImageUrl();
	}

	// 조회수 증가
	public void increaseViewCount() {
		this.viewCount++;
	}

	// 댓글 수 증가
	public void increaseCommentCount() {
		this.commentCount++;
	}

	// 좋아요 수 증가
	public void increaseFavoriteCount() {
		this.favoriteCount++;
	}

	// 좋아요 수 감소
	public void decreaseFavoriteCount() {
		this.favoriteCount--;
	}
}