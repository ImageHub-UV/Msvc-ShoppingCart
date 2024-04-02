package org.imagehub.springcloud.msvc.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcShoppingcartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcShoppingcartApplication.class, args);
	}

}
