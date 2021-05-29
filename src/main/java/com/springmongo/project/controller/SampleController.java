package com.springmongo.project.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value="hello")
public class SampleController {

	@HystrixCommand(threadPoolKey="helloThreadPool")
	public String helloRemoteServiceCall(String firstName,String lastName) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> restExchange = restTemplate.exchange("http://lofical-service-id/name/[ca]{firstName}/{lastName}",HttpMethod.GET,
				null,String.class
				,firstName,lastName);
		return restExchange.getBody();

	}
	
	@RequestMapping(value="{firstName}/{lastName}",method = RequestMethod.GET)
	public String hello(@PathVariable("firstName") String firstName,@PathVariable("lastName") String lastName)  {
		return helloRemoteServiceCall(firstName,lastName);
		
	}
}
