package com.demo.dao;

import java.util.List;

import com.demo.model.Student;

public interface StudentDao {
	
	public void saveStudent(Student student);
	
	public void updateStudent(Student student);
	
	public void deleteStudent(int id);
	
	public Student getStudentById(int id);
	
	public List<Student> getStudentList();

}
