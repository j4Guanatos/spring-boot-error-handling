package org.j4guanatos.spring.boot.config;

import org.j4guanatos.spring.boot.repository.RepositoryPackageMarker;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableMongoAuditing
@EnableMongoRepositories(basePackageClasses = { RepositoryPackageMarker.class })
public class Config {

}
