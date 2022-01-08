package com.example.reactspringbootjpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder // 빌더패턴으로 객체 생성
@NoArgsConstructor // 매개변수 없는 생성자
@AllArgsConstructor // 모든 매개변수 생성자
@Data // Getter와 Setter 메서드 구현
public class TodoEntity {

	private String id;
	private String userId;
	private String title;
	private boolean done;
}
