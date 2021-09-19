//package com.questionaire;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.questionaire.entity.Student;
//
//@RestController
//public class StudentSam {
//
//	@Autowired
//	RestTemplate restTemplate;
//	
//	public static void main(String args[])
//	{
//	
//		final String uri = "http://localhost:8086/student/100/getStudent";
//		
//	    RestTemplate restTemplate = new RestTemplate();
//	    
//	    
//	    HttpHeaders headers = new HttpHeaders();
//	   // headers.setContentType(MediaType.APPLICATION_JSON);
//	    
//	   //HttpEntity <String> entity = new HttpEntity<String>(headers);
//	    HttpEntity <Object> entity = new HttpEntity<>(headers);
//	    
//	     //ResponseEntity<Student> response = restTemplate.getForEntity(uri, Student.class);
//	    
//	    ResponseEntity<String> response = restTemplate.exchange(uri, 
//	    		HttpMethod.GET,
//	    		entity,
//	    		String.class);
//	     
//	   System.out.println(response.getBody());
//	
//	     
//	}   
//	    
//}
