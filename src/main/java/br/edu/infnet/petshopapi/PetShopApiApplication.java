package br.edu.infnet.petshopapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PetShopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetShopApiApplication.class, args);
	}
}
