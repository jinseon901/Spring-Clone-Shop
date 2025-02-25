package com.example.springfletta;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringflettaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringflettaApplication.class, args);
	}

	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("등록된 빈 목록:");
            Arrays.stream(ctx.getBeanDefinitionNames())
                  .filter(bean -> bean.contains("SecurityFilterChain"))
                  .forEach(System.out::println);
        };
    }
}
