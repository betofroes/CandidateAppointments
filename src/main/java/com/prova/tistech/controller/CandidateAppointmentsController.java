package com.prova.tistech.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.prova.tistech.dto.Candidate;
import com.prova.tistech.dto.Scheduling;
import com.prova.tistech.dto.User;

@Named
@ViewScoped
public class CandidateAppointmentsController {
	
	private final String URL = "http://localhost:8080";
	
	private final String USER = "/user";
	
	private final String SERVICO = "/schedules";

	@Autowired
	private RestTemplate restTemplate;

	private Candidate dto;
	
	private Scheduling[] schedulesCandidate;
	
	public void exec() {
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<User> user = getUser();
	 
		headers.set("Authorization", user.getBody().getToken());
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("taxNumber", dto.getTaxNumber());
	 
		HttpEntity<?> entity = new HttpEntity<Object>(body,headers);
		
		ResponseEntity<Scheduling[]> response = restTemplate.exchange(URL+SERVICO, HttpMethod.GET, entity, Scheduling[].class);
		
		setSchedulesCandidate(response.getBody());
	}

	private ResponseEntity<User> getUser() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		// Create the request body as a MultiValueMap
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("user", dto.getName());
		body.add("password", "admin");
		
		// Note the body object as first parameter!
		HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);
		
		return restTemplate.postForEntity(URL+USER, httpEntity, User.class);
	}

	public Scheduling[] getSchedulesCandidate() {
		if(schedulesCandidate == null) {
			this.schedulesCandidate = new Scheduling[] {};
		}
		return schedulesCandidate;
	}

	public void setSchedulesCandidate(Scheduling[] schedulesCandidate) {
		this.schedulesCandidate = schedulesCandidate;
	}

	public Candidate getDto() {
		if(dto == null) {
			dto = new Candidate();
		}
		return dto;
	}

	public void setDto(Candidate dto) {
		this.dto = dto;
	}
}
