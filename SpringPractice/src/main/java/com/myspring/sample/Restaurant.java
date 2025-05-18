package com.myspring.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component // 스프링에게 해당 클래스가 스프링에서 관리해야 하는 대상
@Data // getter, setter, toString 등 포함
public class Restaurant {
	
	@Setter(onMethod_ = @Autowired) // 자동으로 setChef() 컴파일 시 생성
	private Chef chef;
}
