package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	// 이미 존재하는 게시물인지 확인 (댓글 테스트)
	private Long[] bnoArr = {393329L, 393328L, 393309L, 393308L};
	
	@Setter(onMethod_ = @Autowired) 
	private ReplyMapper mapper;

	/*
	@Test
	public void testMapper() {
		log.info("Mapper : " + mapper);
	}
	
	@Test
	public void testCreate() {
		IntStream.range(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			
			vo.setBno(bnoArr[i % 4]);
			vo.setReply("댓글 TEST" + i);
			vo.setReplyer("RP" + i);
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead() {
		Long targetRno = 5L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		log.info(vo);
	}
	
	@Test
	public void testDelete() {
		Long targetRno = 1L;
		mapper.delete(targetRno);
	}
	
	@Test
	public void testUpdate() {
		Long targetRno = 9L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("댓글 수정!");
		
		int count = mapper.update(vo);
		
		log.info("UPDATE COUNT : " + count);
	}
	
	@Test
	public void tesetList() {
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		
		replies.forEach(reply -> log.info(reply));
	}
	
	@Test
	public void tesetList2() {
		Criteria cri = new Criteria(2, 10);
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 393349L);
		
		replies.forEach(reply -> log.info(reply));
	}
		
	*/
	
	@Test
	public void testReplyCount() {
		log.info(mapper.getCountByBno(393349L));
	}
}