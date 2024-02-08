package org.hillel.hibernate.session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hillel.hibernate.model.entity.Student;

import java.util.Properties;


public class HibernateSession implements AutoCloseable {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties properties = new Properties();

            properties.setProperty("hibernate.connection.url", System.getenv("DB_URL"));
            properties.setProperty("hibernate.connection.username", System.getenv("DB_USERNAME"));
            properties.setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"));

            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addProperties(properties)
                    .addAnnotatedClass(Student.class)
                    .buildSessionFactory();
        }

        return sessionFactory;
    }


    @Override
    public void close() throws Exception {
        sessionFactory.close();
    }
}
