package com.grinyov.app.main;

import com.grinyov.app.entities.Student;
import com.grinyov.app.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by green on 12.12.2015.
 *
 * Пример запросов с помощью Hibernate
 *
 * Библиотека Hibernate предлагает три вида запросов к БД:
 1) Criteria
 2) SQL
 3) HQL
 */
public class QueryExamples {

    public static void main(String[] args) {


        // queries using Criteria
        //
        //************************************************************************************************************

        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Student.class); //создаем критерий запроса
        //criteria.setMaxResults(50);//ограничиваем число результатов
        List students = criteria.list();//помещаем результаты в список

        /**
         *
         Expression.like — указывает шаблон, где ‘_’ — любой один символ, ‘%’ — любое количество символов
         Expression.isNull — значение поля равно NULL.
         Expression.between — ‘age’ — имя поля, 18 — минимальное значение указанного поля, 25 — его максимальное значение
         Expression.in — указывает диапазон значений конкретного поля
         Expression.disjunction, Expression.or — дизъюнкция (OR) — объединяет в себе несколько других выражений оператором ИЛИ.
         Expression.eq — определяет равенство поля какому-то значению
         */

        List studs = session.createCriteria(Student.class)
                .add(Expression.like("name", "Ivanov%"))
                .add(Expression.between("age", 18, 25))
                .list();

        List studs2 = session.createCriteria(Student.class)
                .add(Expression.like("name", "_van%"))
                .add(Expression.or(
                        Expression.eq("age", new Integer(20)),
                        Expression.isNull("age")
                ))
                .list();

        List studs3 = session.createCriteria(Student.class)
                .add(Expression.in("name", new String[]{"Ivanov Ivan", "Petrov Petia", "Zubin Egor"}))
                .add(Expression.disjunction()
                                .add(Expression.isNull("age"))
                                .add(Expression.eq("age", new Integer(20)))
                                .add(Expression.eq("age", new Integer(21)))
                                .add(Expression.eq("age", new Integer(22)))
                )
                .list();

        //результаты можно отсортировать
        List studs4 = session.createCriteria(Student.class)
                .add(Expression.like("name", "Iv%"))
                .addOrder(Order.asc("name"))//по возрастанию
                .addOrder(Order.desc("age"))//по убыванию
                .list();

        // есть возможность запроса по данным экземпляра класса
        Student s = new Student();
        s.setName("Ivanov Ivan");
        s.setAge(20l);
        List results = session.createCriteria(Student.class)
                .add(Example.create(s))
                .list();
        //поля объекта, имеющие значение null или являющиеся идентификаторами, будут игнорироваться. Example также можно настраивать
        Example example = Example.create(s)
                .excludeZeroes()           //исключает поля с нулевыми значениями
                .excludeProperty("name")  //исключает поле "name"
                .ignoreCase()              //задает независимое от регистра сравнение строк
                .enableLike();             //использует like для сравнения строк
        results = session.createCriteria(Student.class)
                .add(example)
                .list();
        //************************************************************************************************************

        // queries using SQL
        //
        //************************************************************************************************************

        session.createSQLQuery("select * from Student").addEntity(Student.class).list();
        session.createSQLQuery("select id, name, age from Student").addEntity(Student.class).list();

        // В createSQLQuery задается SQL запрос, а с помощью addEntity указывается какая сущность ожидается в результате
        // также можно указывать параметры:

        //  с помощью query.setString мы указываем порядковый номер параметра (?) и значение типа String,
        // которое вместо него подставится. Если значение типа Long, то будет setLong, если Date, то setDate и так далее
        Query query = session.createSQLQuery("select * from Student where name like ?").addEntity(Student.class);
        List result = query.setString(0, "Ivan%").list();
        //ниже имя параметра задано явно, поэтому значение задается параметру по имени
        query = session.createSQLQuery("select * from Student where name like :name").addEntity(Student.class);
        List result2 = query.setString("name", "Ivan%").list();
        //************************************************************************************************************

        // queries using HQL
        //
        //************************************************************************************************************

        // запрос с помощью SQL производился методом createSQLQuery, то в HQL будет просто createQuery
        // и select в начале запроса можно не указывать

        List<Student> studs5 = (List<Student>)session.createQuery("from Student order by name").list();

        // HQL — объектно-ориентированный язык и значение полей можно выбрать и так

        List<String> names = (List<String>)session.createQuery("select stud.name from Student stud order by name").list();

        // или так
        List result3 = session.createQuery("select new list(stud, name, stud.age) from Student as stud").list();
    }
}
