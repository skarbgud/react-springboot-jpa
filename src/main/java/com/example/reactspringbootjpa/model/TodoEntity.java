package com.example.reactspringbootjpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder // 빌더패턴으로 객체 생성
@NoArgsConstructor // 매개변수 없는 생성자
@AllArgsConstructor // 모든 매개변수 생성자
@Data // Getter와 Setter 메서드 구현
@Entity // 엔티티 이름으로 매칭 @Entity(name="TodoEntity")처럼 엔티티에 이름을 붙일 수 있다
@Table(name = "Todo") // 해당 엔티티가 Todo테이블로 매핑된다.
public class TodoEntity {

	/*
	Hibernate가 제공하는 기본 Generator가 아니라 나만의 Generator를 사용하고 싶은 경우 이용(기본 Generator로는 INCREMENTAL, SEQUENCE, IDENTITY등이 있다)
	문자열 형태의 uuid의 사용을 위해 커스텀 generaotr를 만들었다, UUID의 사용을 위해 GenericGenerator의 매개변수를 strategy로 "uuid"를 넘겼다.
	이렇게 uuid를 사용하는 "system-uuid"라는 이름의 GenericGenerator를 만들었고, 이 Generator는 GeneratedValue가 참조해서 사용한다.
	 */
	@Id // 기본 키
	@GeneratedValue(generator = "system-uuid") // id 자동 생성, generator를 어떤 방식으로 ID를 자동으로 생성할 지 지정(system-uuid라는 generator를 사용)
	@GenericGenerator(name="system-uuid", strategy = "uuid") 
	private String id;
	private String userId;
	private String title;
	private boolean done;
}
