package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.security.JwtService;

@Service
public class UserService {
	
//	@Autowired JwtService jwtService;
	
	public User loadUserByUsername(String username) {
		User us = new User();
	
			us.setId(1);
			us.setUsername("dong");
			us.setPassword("kkaa");
			us.setRoles(new String[] { "ROLE_ADMIN" });
			return us;
	
	
	}
	
	public static List<User> lstUserExample() {
		Random rand = new Random();
		List<User> lstUser =  new ArrayList<>();
		JwtService jwt = new JwtService();
		for(int i =0; i < 5; i ++) {
			int id = rand.nextInt(50) + 1;
			String username = "user_"+id;
			String pass = "pass_"+id;
			User us = new User();
			us.setId(id);
			us.setPassword(pass);
			us.setUsername(username);
			us.setToken(jwt.generateTokenLogin(username));
			lstUser.add(us);
		}
		User us = new User();
		us.setId(55);
		us.setPassword("55");
		us.setUsername("55");
		lstUser.add(us);
		return lstUser;
	}
}
