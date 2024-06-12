package com.example.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisDemoApplication.class, args);
	}

//	@Bean
//	CommandLineRunner loadData(PeopleRepository repo) {
//		return args -> {
//			repo.deleteAll();
//			String thorSays = "The Rabbit Is Correct, And Clearly The Smartest One Among You.";
//			Address thorsAddress = Address.of("248", "Seven Mile Beach Rd", "Broken Head",
//					"NSW", "2481", "Australia");
//			Person thor = Person.of("Chris", "Hemsworth", 38, thorSays,
//					new Point(153.616667, -28.716667), thorsAddress,
//					Set.of("hammer", "biceps", "hair", "heart"));
//			repo.save(thor);
//		};
//	}

}
