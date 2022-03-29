package za.co.wethinkcode.dms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DeveloperManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeveloperManagementSystemApplication.class, args);
	}

}
