package com.learning.gym.star;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GymStarApplication {


    public static void main(String[] args) {
		SpringApplication.run(GymStarApplication.class, args);

    }

//	private static final Logger log = LoggerFactory.getLogger(GymStarApplication.class);
//	@Bean
//	@Autowired
//	public CommandLineRunner demo( @Qualifier("database access") GymRepository repository) {
//		return (args)->{
//			String[] gymdata = repository.getGymById(1);
//			String gymString = Arrays.toString(gymdata);
//			log.info("Gym found with getGymById(1):");
//			log.info("--------------------------------");
//			log.info(gymString);
//			log.info("");
//		};
//	}
}
