package com.grinyov.app.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by green on 09.12.2015.
 *
 * Класс отвечает за обработку данного hibernate.cfg.xml файла
 * и установление соединения с нашей базой данных
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        try {
            //creates the session factory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
