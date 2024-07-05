package io.muzoo.ssc.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;


@SpringBootApplication
@EnableJdbcRepositories(basePackages = "io.muzoo.ssc.Project.data")
@PropertySource("classpath:application.properties")
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
