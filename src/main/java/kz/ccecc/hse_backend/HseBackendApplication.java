package kz.ccecc.hse_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class HseBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HseBackendApplication.class, args);
	}

}
