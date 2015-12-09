package com.grinyov.app.dao.impl;

import com.grinyov.app.dao.StudentDAO;
import com.grinyov.app.entities.Student;
import com.grinyov.app.util.HibernateUtil;

import org.hibernate.Session;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by green on 09.12.2015.
 *
 * Класс реализующий интерфейс для работы с таблицей student нашей БД
 *
 */
public class StudentDAOImpl implements StudentDAO {

    public void addStudent(Student student) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateStudent(Student student) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Student getStudentById(Long id) throws SQLException {
        Session session = null;
        Student student = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            student = (Student) session.load(Student.class, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return student;
    }

    public List<Student> getAllStudents() throws SQLException {
        Session session = null;
        List<Student> students = new ArrayList<Student>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            students = session.createCriteria(Student.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return students;
    }

    public void deleteStudent(Student student) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}