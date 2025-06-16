package com.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	// 텍스트 반환
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
	
	// VO 반환
	@GetMapping(value = "/getSample",
				produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
							MediaType.APPLICATION_XML_VALUE}) // JSON, XML 값을 보여주기 위한 Produce 설정
	public SampleVO getSample() {
		return new SampleVO(112, "스타", "로드");
	}
	
	// 리스트 반환
	@GetMapping(value = "/getList",
				produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
							MediaType.APPLICATION_XML_VALUE})
	public List<SampleVO> getList(){
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + " First Val", i + " Last Val"))
				.collect(Collectors.toList());
	}
	
	// 맵 반환
	@GetMapping(value = "/getMap",
				produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
							MediaType.APPLICATION_XML_VALUE})
	public Map<String, SampleVO> getMap(){
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111, "그루트", "주니어"));
		
		return map;
	}
	
	// HTTP 응답 반환 엔티티
	@GetMapping(value = "/check", params = {"height", "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		SampleVO vo = new SampleVO(0, "" + height, "" + weight);
		
		ResponseEntity<SampleVO> result = null;
		
		// 개발자 도구 - NetWork - Name에서 Headers 정보 확인
		// http://localhost:8080/controller/sample/check?height=150&weight=50
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	
	// @PathVariable : 주소 경로를 파라미터로 사용
	// http://localhost:8080/controller/sample/product/bags/1234
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
		@PathVariable("cat") String cat,
		@PathVariable("pid") Integer pid) {
		
		return new String[] {"category : " + cat, "productid: " + pid};
	}
}