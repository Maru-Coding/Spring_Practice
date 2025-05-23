package com.myspring.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) // 이 Test 코드가 스프링 실행 역할임을 알려준다
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // 스프링 Beans(객체)로 등록 
@Log4j // 로그 기록용
public class SampleTests {
	
	// Autowired : 인스턴스 변수를 스프링으로부터 자동 주입
	@Setter(onMethod_ = { @Autowired })
	private Restaurant restaurant;
	
	@Test // 테스트 대상 표시
	public void testExist() {
		assertNotNull(restaurant); // restaurant 변수가 null이 아니어야만 테스트 성공
		
		log.info(restaurant);
		log.info("--------------------------");
		log.info(restaurant.getChef());
	}
}
