package telerikacademy.daredevil.extensionrepository.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                //.addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return sessionFactory;
    }
}
