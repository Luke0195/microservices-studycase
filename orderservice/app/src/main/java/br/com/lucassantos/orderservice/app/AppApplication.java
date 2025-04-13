package br.com.lucassantos.orderservice.app;

import br.com.lucassantos.orderservice.app.properties.QueueProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Queue;

@EnableConfigurationProperties(QueueProperties.class)
@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
