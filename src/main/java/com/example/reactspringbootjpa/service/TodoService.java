package com.example.reactspringbootjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reactspringbootjpa.model.TodoEntity;
import com.example.reactspringbootjpa.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	public List<TodoEntity> create(final TodoEntity entity) {
		// Validations 
		validate(entity);
		
		repository.save(entity);
		
		log.info("Entity Id : {} is saved", entity.getId());
		
		return repository.findByUserId(entity.getUserId());
	}
	
	private void validate(final TodoEntity entity) {
		if (entity == null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null.");
		}
		if (entity.getUserId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}
}
