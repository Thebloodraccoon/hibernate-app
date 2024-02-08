package org.hillel.hibernate.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hillel.hibernate.model.entity.Student;

import java.util.List;

@AllArgsConstructor
public class StudentMySqlDAO implements StudentDAO {
    private Session session;

    @Override
    public Student save(Student student) {
        Transaction transaction = null;
        try {

            transaction = session.beginTransaction();

            session.persist(student);
            session.flush();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return student;
    }

    @Override
    public Student delete(Student student) {
        Transaction transaction = null;
        try {

            transaction = session.beginTransaction();

            session.remove(student);
            session.flush();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return student;
    }

    @Override
    public void update(Student student) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            session.merge(student);
            session.flush();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Student getStudentById(Long id) {
        return session.find(Student.class, id);
    }

    @Override
    public List<Student> getAllStudents() {
        Transaction transaction = null;
        List<Student> students = null;
        try {
            transaction = session.beginTransaction();

            Query<Student> query = session.createQuery("FROM Student", Student.class);
            students = query.getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction!= null) {
                transaction.rollback();
            }
        }
        return students;
    }
}
