package br.com.catolica.crud_aluno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.catolica.crud_aluno")
public class CrudAlunoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudAlunoApplication.class, args);
	}
}
