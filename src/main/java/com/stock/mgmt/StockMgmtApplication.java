package com.stock.mgmt;

import com.stock.mgmt.repositories.BrandRepository;
import com.stock.mgmt.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;


@SpringBootApplication
public class StockMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMgmtApplication.class, args);
	}

	@Bean
	ServletRegistrationBean h2servletRegistration(){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}

	@Bean
	public CommandLineRunner executeQuery(BrandRepository brandRepository,
										  ProductRepository productRepository) {
		return (args) -> {

			brandRepository.getAllBrands().forEach(System.out::println);

			productRepository.getAllProducts().forEach(System.out::println);
		};
	}

}
