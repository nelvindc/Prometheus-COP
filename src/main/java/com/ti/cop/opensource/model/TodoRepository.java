package com.ti.cop.opensource.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class TodoRepository {
	
	private Map<Integer, Todo> todoMap = new HashMap<Integer, Todo>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		put(1,sampleTodo());
	}};


	public Map<Integer, Todo> getTodoMap() {
		return todoMap;
	}

	public void setTodoMap(Map<Integer, Todo> todoMap) {
		this.todoMap = todoMap;
	}
	
	private Todo sampleTodo(){
		Todo sample = new Todo();
		sample.setId(1);
		sample.setDescription("sample task");
		return sample;
	}
	
	
	
	
	
	

}
