package example.myVocabulary;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class MyVocabularyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyVocabularyApplication.class, args);
	}

	@Component
	class Runner implements CommandLineRunner {
		@Override
		public void run(String... args) {
			System.out.println("FOR WORKING WITH VOCABULARY VISIT");
			System.out.println("http://localhost:8080");
		}
	}
}
