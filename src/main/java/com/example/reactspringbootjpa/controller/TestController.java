package com.example.reactspringbootjpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.reactspringbootjpa.dto.ResponseDTO;
import com.example.reactspringbootjpa.dto.TestRequestBodyDTO;

@RestController
@RequestMapping("test") // 리소스
public class TestController {
	
	@GetMapping
	public String testController() {
		return "Hello World!";
	}
	
	@GetMapping("/testGetMapping")
	public String testControllerWithPath() {
		return "Hellow World! testGetMapping";
	}
	
	@GetMapping("/{id}")
	public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
		return "Hello World! ID " + id;
	}
	
	@GetMapping("/testRequestParam")
	public String testControllerRequestParam(@RequestParam(required = false) int id) {
		return "Hello World! ID " + id;
	}
	
	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return "Hello World! ID " + testRequestBodyDTO.getId() + " message : " + testRequestBodyDTO.getMessage();
	}
	
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody() {
		List<String> list = new ArrayList<>();
		list.add("Hello World! I'm ReponseDTO");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		// http status 400으로 설정
		return response;
	}
	
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerReponseEntity() {
		List<String> list = new ArrayList<>();
		list.add("Hello World! I'm ReponseEntity");
		list.add("And HTTP Status is 400");
		ResponseDTO<String> reponse = ResponseDTO.<String>builder().data(list).build();
		// http status를 400으로 설정
		return ResponseEntity.badRequest().body(reponse);
	}
	
}
