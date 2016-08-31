package com.demo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.demo.dao.StudentDao;
import com.demo.model.Student;

public class StudentDaoImpl implements StudentDao {

	private JdbcTemplate jdbcTemplate;

	public static final String SELECT_STUDENT_ALL = "SELECT * FROM student";
	public static final String SELECT_STUDENT_BY_ID = "SELECT * FROM student WHERE id = ?";
	public static final String UPDATE_STUDENT = "UPDATE student SET firstName=?, lastName=?, email=?, address=?, telephone=? WHERE id=?";
	public static final String DELETE_STUDENT = "DELETE FROM student WHERE id = ?";
	public static final String CREATE_STUDENT = "INSERT INTO student (firstName, lastName, email, address, telephone) VALUES (?, ?, ?, ?, ?)";

	public StudentDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveStudent(Student student) {
		jdbcTemplate.update(CREATE_STUDENT, student);

	}

	@Override
	public void updateStudent(Student student) {
		jdbcTemplate.update(UPDATE_STUDENT, student);

	}

	@Override
	public void deleteStudent(int id) {
		jdbcTemplate.update(UPDATE_STUDENT, id);

	}

	@Override
	public Student getStudentById(int id) {
		return jdbcTemplate.query(SELECT_STUDENT_BY_ID, new ResultSetExtractor<Student>() {
			 
	        @Override
	        public Student extractData(ResultSet rs) throws SQLException, DataAccessException {
	            if (rs.next()) {
	            	Student student = new Student();
	            	
	            	student.setId(rs.getInt("id"));
					student.setFirstName(rs.getString("firstName"));
					student.setLastName(rs.getString("lastName"));
					student.setEmail(rs.getString("email"));
					student.setAddress(rs.getString("address"));
					student.setTelephone(rs.getString("telephone"));
					
	                return student;
	            }
	 
	            return null;
	        }
	 
	    });
	}

	@Override
	public List<Student> getStudentList() {
		List<Student> studentList = jdbcTemplate.query(SELECT_STUDENT_ALL,
				new RowMapper<Student>() {

					@Override
					public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
						Student student = new Student();

						student.setId(rs.getInt("id"));
						student.setFirstName(rs.getString("firstName"));
						student.setLastName(rs.getString("lastName"));
						student.setEmail(rs.getString("email"));
						student.setAddress(rs.getString("address"));
						student.setTelephone(rs.getString("telephone"));

						return student;
					}

				});

		return studentList;
	}

}
