package com.grinyov.app.main;

import com.grinyov.app.dao.Factory;
import com.grinyov.app.entities.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by green on 09.12.2015.
 *
 * Класс запуска приложения
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        //Создадим двух студентов
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();

        //Проинициализируем их
        s1.setName("Ivanov Ivan");
        s1.setAge(21l);
        s2.setName("Petrov Petr");
        s2.setAge(24l);
        s3.setName("Markova Svetlana");
        s3.setAge(22l);

        //Сохраним их в бд, id будут сгенерированы автоматически
        Factory.getInstance().getStudentDAO().addStudent(s1);
        Factory.getInstance().getStudentDAO().addStudent(s2);
        Factory.getInstance().getStudentDAO().addStudent(s3);

        //Выведем всех студентов из бд
        List<Student> studs = Factory.getInstance().getStudentDAO().getAllStudents();
        System.out.println("========Все студенты=========");
        for(int i = 0; i < studs.size(); ++i) {
            System.out.println("Имя студента : " + studs.get(i).getName() + ", Возраст : " + studs.get(i).getAge() +",  id : " + studs.get(i).getId());
            System.out.println("=============================");
        }
    }
}
