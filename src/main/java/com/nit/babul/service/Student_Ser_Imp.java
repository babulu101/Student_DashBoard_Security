package com.nit.babul.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nit.babul.entity.Student;
import com.nit.babul.repository.Student_Repo;

@Service
public class Student_Ser_Imp implements Student_Ser,UserDetailsService{
	@Autowired
	private Student_Repo repo;
	
	@Autowired
	private BCryptPasswordEncoder password;
	
	
	@Override
	public int save(Student stu) {
		String pass=password.encode(stu.getPassword());
		stu.setPassword(pass);
		Student student=repo.save(stu);
		return student.getId();
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Student student=repo.findByName(username);
		return new User(student.getName(),student.getPassword(),Collections.emptyList());
	}
}
