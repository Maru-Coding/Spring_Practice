package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	@RequestMapping("")
	public void basic() {
		log.info("basic......");
	}
	
	@RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get......");
	}
	
	@GetMapping("basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get......");
	}
	
	// DTO 파라미터 수집
	// 주소 호출 방식 : http://localhost:8080/sample/ex01?name=AAA&age=3	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("ex01 DTO is ... " + dto);
		return "ex01";
	}
	
	// DTO 파라미터 변환 및 수집
	// 주소 호출 방식 : http://localhost:8080/sample/ex02?name=AAA&age=3
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name : " + name);
		log.info("age : " + age);
		
		return "ex02";
	}
	
	// 리스트 처리
	// 주소 호출 방식 : http://localhost:8080/sample/ex02List?ids=111&ids=222&ids=333
	@GetMapping("ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		log.info("List ids : " + ids);
		return "ex02List";
	}
	
	// 배열 처리
	// 주소 호출 방식 : http://localhost:8080/sample/ex02Array?ids=111&ids=222&ids=333
	@GetMapping("ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		log.info("Array ids : " + Arrays.toString(ids));
		return "ex02Array";
	}
	
	// 객체 리스트 처리
	// 주소 호출 방식 : http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B2%5D.name=bbb
	// list[0].name > list%5B0%5D.name : Tomcat 버전에 따른 특수문자 비허용 및 [] 인코딩
	@GetMapping("ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list);
		return "ex02Bean";
	}
	
	// 파라미터 바인딩 및 변환 (날짜)
	// @InitBinder 또는 @DateTimeFormat 중 하나만 사용 (TodoDTO.java)
	// 간단한 날짜 정도는 @DateTimeFormat, 복잡한 객체라면 @InitBinder가 유리
	/*
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(dateFormat, false));
	}
	*/
	
	// 위 함수로 날짜 파라미터 바인딩
	// 주소 호출 방식 : http://localhost:8080/sample/ex03?title=test&dueDate=2018/01/01
	@GetMapping("ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo" + todo);
		return "ex03";
	}
}