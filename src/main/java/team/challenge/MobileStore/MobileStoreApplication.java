package team.challenge.MobileStore;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMongock
public class MobileStoreApplication {


	public static void main(String[] args) {
		SpringApplication.run(MobileStoreApplication.class, args);
	}
}