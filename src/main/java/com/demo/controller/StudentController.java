package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.dao.StudentDao;
import com.demo.model.Student;

@Controller
public class StudentController {

	@Autowired
	private StudentDao studentDao;

	@RequestMapping(value = "/")
	public ModelAndView getStudentList(ModelAndView modelAndView) {
		List<Student> studeList = studentDao.getStudentList();
		modelAndView.addObject("studeList", studeList);
		modelAndView.setViewName("home");

		return modelAndView;
	}
}
