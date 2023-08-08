package com.sun.board.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.board.dto.request.board.PostCommentRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="comment")
@Table(name="comment")
public class CommentEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commentNumber;
	private int boardNumber;
	private String userEmail;
	private String contents;
	private String writeDatetime;

	public CommentEntity(Integer boardNumber, PostCommentRequestDto dto) {
    Date now = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String writeDatetime = simpleDateFormat.format(now);

    this.boardNumber = boardNumber;
    this.userEmail = dto.getUserEmail();
    this.contents = dto.getContents();
    this.writeDatetime = writeDatetime;
  }
}
