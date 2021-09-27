package com.questionaire.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.questionaire.controller.Response;

public class ResponseUtil {

	public static ResponseEntity<Response> getResponse(Integer code, String message, Object data) {
		Response response = new Response();
		ResponseEntity<Response> responseEntity = null;
		if (code == 200) {
			response.setStatusCode(code);
			response.setStatusText(message);
			response.setData(data);
			responseEntity = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		}
		return responseEntity;
	}

	public static ResponseEntity<Response> getResponse(Integer code, String message) {
		Response response = new Response();
		ResponseEntity<Response> responseEntity = null;
		if (code == 404) {
			response.setStatusCode(code);
			response.setStatusText(message);
			responseEntity = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
		} else if (code == 422) {
			response.setStatusCode(code);
			response.setStatusText(message);
			responseEntity = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			response.setStatusCode(500);
			response.setStatusText(message);
			responseEntity = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
}
