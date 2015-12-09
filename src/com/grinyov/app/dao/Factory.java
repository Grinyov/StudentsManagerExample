package com.grinyov.app.dao;

import com.grinyov.app.dao.impl.StudentDAOImpl;

/**
 * Created by green on 09.12.2015.
 *
 * Класс-фабрика для создания и управления реализаций dao, от которых и будем вызывать методы для работы с бд
 *
 */
public class Factory {

    private static StudentDAO studentDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public StudentDAO getStudentDAO(){
        if (studentDAO == null){
            studentDAO = new StudentDAOImpl();
        }
        return studentDAO;
    }
}
