package com.nit.babul.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import com.nit.babul.entity.Student;


public interface Student_Repo extends JpaRepository<Student, Integer> {
	public Student findByName(String name);

}
