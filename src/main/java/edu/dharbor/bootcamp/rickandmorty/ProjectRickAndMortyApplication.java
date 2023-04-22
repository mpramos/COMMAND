package edu.dharbor.bootcamp.rickandmorty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories
@EnableMongoRepositories
public class ProjectRickAndMortyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectRickAndMortyApplication.class, args);
    }
}
