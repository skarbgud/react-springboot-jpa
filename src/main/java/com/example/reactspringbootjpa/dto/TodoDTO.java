package com.example.reactspringbootjpa.dto;

import com.example.reactspringbootjpa.model.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 	클라이언트로 반환할때 Model 자체를 그대로 리턴하는 경우는 별로없다.
 	데이터를 전달하는데 사용하는 DTO(Data Transfer Object)로 변환해서 리턴한다.
 	
 	이유
 	1. 비스니스 로직을 캡슐화 하기 위해서
 	모델은 DB 테이블 구조와 매우 유사하다. 모델이 갖고 있는 필드들은 테이블의 스키마와 비슷할 확률이 높다
 	대부분 비즈니스는 외부인이 자사 DB의 스키마를 아는 것을 윈치 않는다.
 	이때 DTO처럼 다른 오브젝트로 바꿔 반환하면 외부 사용자에게 서비스 내부의 로직, 데이터베이스의 구조등을 숨길 수 있다.
 	
 	2. 클라이언트가 필요한 정보를 모델이 전부 포함하지 않는 경우가 많기 때문이다.
 	대표적인 예로 에러 메세지가 있다.
 	만약 서비스 실행 도중 사용자 에러가 나면 이 에러 메세지를 모델에 포함하기는 애매하다.
 	이런 경우 DTO에 에러 메세지 필드를 선언하고 DTO에 포함하면 된다.
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {

	private String id;
	private String title;
	private boolean done;
	
	public TodoDTO(final TodoEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.done = entity.isDone();
	}
}
