package com.microservice.product;

import com.microservice.product.config.AppConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestHibernate {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            SessionFactory sessionFactory = context.getBean(SessionFactory.class);
            try (Session session = sessionFactory.openSession()) {
                session.doWork(connection -> {
                    if (!connection.isValid(5)) {
                        throw new java.sql.SQLException("Connexion MySQL invalide");
                    }
                });
            }
            System.out.println("Contexte Spring, SessionFactory et connexion MySQL initialisés avec succès !");
        }
    }
}
