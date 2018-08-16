package telerikacademy.daredevil.extensionrepository.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import telerikacademy.daredevil.extensionrepository.models.Product;

@Configuration
public class AppConfig {
    @Bean
    public SessionFactory createSessionFactory(){

        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }
}
