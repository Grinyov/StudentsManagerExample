package com.grinyov.app.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
/**
 * Created by green on 09.12.2015.
 *
 * Класс описывающий сущность Студент
 */
@Entity
@Table(name="student")
public class Student {

    private Long id;
    private String name;
    private Long age;

    public Student(){
        name = null;
    }

    public Student(Student student){
        name = student.getName();
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    public Long getId() {
        return id;
    }

    @Column(name="name")
    public String getName(){
        return name;
    }

    @Column(name="age")
    public Long getAge(){
        return age;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(Long age){
        this.age = age;
    }
}
