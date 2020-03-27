package es.vir2al.fwk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan({"es.vir2al.fwk"})
public class FwkApplication {

	public static void main(String[] args) {
		SpringApplication.run(FwkApplication.class, args);
	}

}
