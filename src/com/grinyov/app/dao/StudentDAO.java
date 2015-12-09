package com.grinyov.app.dao;

import com.grinyov.app.entities.Student;

import java.sql.SQLException;
import java.util.List;
/**
 * Created by green on 09.12.2015.
 *
 * Интерфейс определяющий набор операций для работы с сущностью Студент
 */
public interface StudentDAO {

    public void addStudent(Student student) throws SQLException;   //добавить студента
    public void updateStudent(Student student) throws SQLException;//обновить студента
    public Student getStudentById(Long id) throws SQLException;    //получить стедента по id
    public List getAllStudents() throws SQLException;              //получить всех студентов
    public void deleteStudent(Student student) throws SQLException;//удалить студента

}
