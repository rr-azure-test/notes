package pl.sda.azure.notes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demoData(NoteRepository repo) {
		return args -> {
			repo.save(new Note("Zakupy", 1));
			repo.save(new Note("Bateria", 3));
			repo.save(new Note("Serial", 5));
			repo.save(new Note("Lekarz", 1));
			repo.save(new Note("Opłaty", 2));
		};
	}
}
