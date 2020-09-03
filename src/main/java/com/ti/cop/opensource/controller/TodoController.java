package com.ti.cop.opensource.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ti.cop.opensource.model.Metrics;
import com.ti.cop.opensource.model.Todo;
import com.ti.cop.opensource.model.TodoRepository;

import io.prometheus.client.SimpleTimer;

@RestController
@ControllerAdvice
@RequestMapping("/samplerest/todo")
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepo;

	
	@RequestMapping(value = "/ping",
			method = RequestMethod.GET,
			produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<String> ping()   {
		
/*		Metrics.HTTP_REQUEST_SERVICE_OPERATION.labels("todo", "ping", "GET").inc();
		Metrics.HTTP_REQUEST_SERVICE_TOTAL.labels("todo").inc();*/

		return new ResponseEntity<>("Welcome!", HttpStatus.OK );		
	}
	
	@RequestMapping(value = "/list",
			method = RequestMethod.GET,
			produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<Todo>> getTodoList()   {
		
		//SimpleTimer requestTimer = new SimpleTimer();
		
		List<Todo> valueList = new ArrayList<>(todoRepo.getTodoMap().values());
		
/*		Metrics.HTTP_REQUEST_SERVICE_OPERATION.labels("todo", "list", "get").inc();
		Metrics.HTTP_REQUEST_SERVICE_TOTAL.labels("todo").inc();
		
		Metrics.RESPONSE_TIME_HISTOGRAM.labels("todo", "list", "GET").observe(requestTimer.elapsedSeconds());*/
		
		return new ResponseEntity<>(valueList, HttpStatus.OK );		
	}
	
	@RequestMapping(value = "/",
			method = RequestMethod.POST,
			produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Todo> addTodo(  
		@RequestBody String requestbody){
		
		List<Integer> ids = new ArrayList<>(todoRepo.getTodoMap().keySet());
		Integer loopCount = 1;
		Collections.sort(ids);
		for (Integer id: ids){
			if (id > loopCount){
				break;
			} else {
				loopCount++;
			}
		}
		int addedId = loopCount;
		Todo todo = new Todo();
		todo.setId(addedId);
		todo.setDescription(requestbody);
		todoRepo.getTodoMap().put(addedId, todo);
		return new ResponseEntity<>(todo, HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> deleteTodo(
			@PathVariable("id") Integer todoId)   {
		todoRepo.getTodoMap().remove(todoId);
		return new ResponseEntity<>(todoId, HttpStatus.OK );		
	}
	
	

}
