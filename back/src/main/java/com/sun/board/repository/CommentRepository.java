package com.sun.board.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sun.board.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	
	// delete 는 반환값이 없음 그래서 void
	@Transactional
	void deleteByBoardNumber(Integer boardNumber);

}
