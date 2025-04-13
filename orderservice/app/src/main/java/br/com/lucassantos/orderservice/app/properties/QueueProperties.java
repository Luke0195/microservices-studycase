package br.com.lucassantos.orderservice.app.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "queue")
public record QueueProperties(String name) {
}
