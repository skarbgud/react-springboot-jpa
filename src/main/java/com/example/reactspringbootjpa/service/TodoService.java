package com.example.reactspringbootjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reactspringbootjpa.model.TodoEntity;
import com.example.reactspringbootjpa.persistence.TodoRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository repository;
	
	public String testService() {
		// TodoEntity 생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").userId("member").build();
		// TodoEntity 저장
		repository.save(entity);
		// TodoEntity 검색
		TodoEntity savedEntity = repository.findByUserId(entity.getUserId()).get(0);
		return "title: " + savedEntity.getTitle() + ", userId: " + savedEntity.getUserId();
	}
}
