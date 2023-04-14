package jeju.oneroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OneroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneroomApplication.class, args);
	}

}
