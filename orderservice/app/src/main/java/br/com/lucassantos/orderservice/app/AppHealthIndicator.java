package br.com.lucassantos.orderservice.app;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class AppHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        try{
            return Health.up()
                    .withDetail("database", "Ok")
                    .withDetail("rabbitmq", "Ok")
                    .withDetail("ping", "Ok")
                    .withDetail("rabbit", "Ok").build();
        }catch (Exception exception){
         return  Health.down(exception).build();
        }
    };
}
