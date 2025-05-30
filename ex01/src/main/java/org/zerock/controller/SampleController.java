package org.zerock.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
	
	
	/* ----------------------------------------- */
	// 모델 활용하기
	// @ModelAttribute : 모델에 파라미터 전달
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto : " + dto);
		log.info("page : " + page);
		return "/sample/ex04";
	}
	
	// void 컨트롤러
	@GetMapping("/ex05")
	public void ex05() {
		log.info("/ex05......");
	}
	
	// 객체 반환 컨트롤러 (pom.xml에 jackson-databind 추가)
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06......");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(20);
		dto.setName("마루설아");
		
		return dto;
	}
	
	// ResponseEntity 반환
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("ex07......");
		
		String msg = "{\"name\" : \"마루설아\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	
	// 파일 업로드
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("exUpload......");
	}
	
	// 파일 업로드 (action)
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		
		files.forEach(file -> {
			log.info("----------------------");
			log.info("name : " + file.getOriginalFilename());
			log.info("size : " + file.getSize());
		});
	}
}