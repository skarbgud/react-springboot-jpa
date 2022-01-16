package com.example.reactspringbootjpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reactspringbootjpa.dto.ResponseDTO;
import com.example.reactspringbootjpa.dto.TodoDTO;
import com.example.reactspringbootjpa.model.TodoEntity;
import com.example.reactspringbootjpa.service.TodoService;

import lombok.Builder;

@RestController
@RequestMapping("todo")
public class TodoController {

	@Autowired
	private TodoService service;
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo() {
		String str = service.testService(); // 테스트 서비스 사용
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
		try {
			String temporaryUserId = "temporary-user"; // temporary user id.
			
			// (1) TodoEntity로 변환한다.
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			// (2) id를 null로 초기화한다. 생성 당시에는 id가 없어야 하기 때문이다.
			entity.setId(null);
			
			// (3) 임시 유저 아이디를 설정 해 준다. 이 부분은 인증과 인가 부분에서 수정 예정. 임시로 사용자 temporary-user만 로그인 없이 사용할 수 있다.
			entity.setUserId(temporaryUserId);
			
			// (4) 서비스를 이용해 Todo 엔티티를 생성한다.
			List<TodoEntity> entities = service.create(entity);
			
			// (5) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환한다.
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			// (6) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			// (7) 변환된 ResponseDTO를 리턴한다.
			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			// (8) 혹시나 예외가 나는 경우 dto 대신 error 메세지를 넣어 리턴한다.
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response); 
		}
	}
}
