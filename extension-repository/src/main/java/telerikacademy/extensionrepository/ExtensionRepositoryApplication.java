package telerikacademy.extensionrepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import telerikacademy.extensionrepository.areas.files.config.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ExtensionRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExtensionRepositoryApplication.class, args);
    }
}
