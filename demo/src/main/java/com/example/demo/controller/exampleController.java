package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JwtService;
import com.example.demo.service.UserService;

@RestController
public class exampleController {
	
	@Autowired UserService userService;
	
	@Autowired JwtService jwtService;
	
	@RequestMapping("/test")
	public ResponseEntity<?> getUser(){
		return new ResponseEntity<>(userService.lstUserExample(), HttpStatus.OK);
	}
	
	@RequestMapping("/user")
	public ResponseEntity<?> getUsers(){
		return new ResponseEntity<>(userService.lstUserExample(), HttpStatus.OK);
	}
	
	@RequestMapping("/login")
	public ResponseEntity<?> login(@RequestParam()String user){
		return new ResponseEntity<>(jwtService.generateTokenLogin(user), HttpStatus.OK);
	}
	
}
