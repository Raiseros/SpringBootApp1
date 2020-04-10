package ru.holyav.springbootapp.crudloginform.service;

import ru.holyav.springbootapp.crudloginform.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {

    public List<Student> getStudents();

    public void saveStudent(Student theStudent) throws SQLException;

    public void updateStudent(Student theStudent);

    public Student getStudent(long theId);

    public void deleteStudent(long theId);

    public Student findByUserName(String firstName);

    public String findByUserRole(String firstName);
}
