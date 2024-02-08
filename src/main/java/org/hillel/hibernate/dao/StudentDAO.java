package org.hillel.hibernate.dao;

import org.hillel.hibernate.model.entity.Student;

import java.util.List;

public interface StudentDAO {
    Student save(Student student);
    Student delete(Student student);
    void update(Student student);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
}
