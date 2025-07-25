package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {
	public void register(BoardVO board);
	
	public BoardVO get(Long bno);
	
	public boolean modify(BoardVO board);
	
	public boolean remove(Long bno);
	
	// 페이정 처리를 위한 주석
	// public List<BoardVO> getList();
	
	// 페이징 처리를 위한 추가
	public List<BoardVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
}
