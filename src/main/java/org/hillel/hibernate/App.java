package org.hillel.hibernate;

import org.hibernate.SessionFactory;
import org.hillel.hibernate.dao.StudentDAO;
import org.hillel.hibernate.dao.StudentMySqlDAO;
import org.hillel.hibernate.model.entity.Student;
import org.hillel.hibernate.session.HibernateSession;


import java.util.List;

public class App {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateSession.getSessionFactory()){
            StudentDAO studentDAO = new StudentMySqlDAO(sessionFactory.openSession());

            studentDAO.save(new Student("Andrew", "andrew@gmail.com"));


            Student studentById = studentDAO.getStudentById(4L);
            System.out.println("Student 4: " + studentById.toString());

            Student deletedStudent = studentDAO.delete(studentById);
            System.out.println("Deleted student " + deletedStudent.getName());


            List<Student> allStudents1 = studentDAO.getAllStudents();
            for (Student student: allStudents1) {
                System.out.println(student);
            }

            Student firstStudent = studentDAO.getStudentById(1L);
            System.out.println(firstStudent);

            firstStudent.setName("Alice");
            firstStudent.setEmail("alice@gmail.com");
            studentDAO.update(firstStudent);
            System.out.println("Apdeting student");

            List<Student> allStudents2 = studentDAO.getAllStudents();
            for (Student student: allStudents2) {
                System.out.println(student);
            }
        }
    }
}