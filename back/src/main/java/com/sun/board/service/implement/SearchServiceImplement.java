package com.sun.board.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sun.board.service.SearchService;

@Service
public class SearchServiceImplement implements SearchService {

	@Override
	public ResponseEntity<?> getPopularList() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getPopularList'");
	}

	@Override
	public ResponseEntity<?> getRelationList(String searchWord) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getRelationList'");
	}
}