package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		// mapper.getList().forEach(board -> log.info(board));
	}
	
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		// 테스트 시 주석 해제
		// mapper.insert(board);
		
		// log.info("========== testInsert() ==========");
		// log.info(board);
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 select key");
		board.setContent("새로 작성하는 내용 select key");
		board.setWriter("newbie2");
		
		// 테스트 시 주석 해제
		// mapper.insertSelectKey(board);
		
		// log.info("========== testInsertSelectKey() ==========");
		// log.info(board);
	}
	
	@Test
	public void testRead() {
		// 존재하는 게시물 번호로 테스트
		// BoardVO board = mapper.read(5L);
		
		// log.info("========== testRead() ==========");
		// log.info(board);
	}
	
	@Test
	public void testDelete() {
		// log.info("Delete Count: " + mapper.delete(5L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		
		board.setBno(3L);
		board.setTitle("수정된 제목123123");
		board.setContent("수정된 내용3344");
		board.setWriter("설아");
		
		int count = mapper.update(board);
		log.info("UPDATE COUNT : " + count);
	}
}
