package walter.duncan.dndwebapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import walter.duncan.dndwebapi.services.filemanagement.StorageService;

@SpringBootApplication
public class DndWebApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(DndWebApiApplication.class, args);
	}

    // This cleans the uploads directory on startup. This is NOT recommended in a production environment!.
    // It is also responsible for creating the uploads directory through StorageService's .init()
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.removeAll();
            storageService.init();
        };
    }
}