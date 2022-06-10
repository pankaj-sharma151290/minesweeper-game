package com.ing.assignment.controller;

import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

	@InjectMocks
	private GameController controller;

	@BeforeClass
	public void setUp() {
	//
	}

	@Test
	@DisplayName(value = "Service Running Status Test")
	public void statusCheck() {
		ResponseEntity<String> responseEntity = controller.checkStatus();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

}
