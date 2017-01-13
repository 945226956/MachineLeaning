package com.machinelearning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * run sprint boot app
 * @author 945226956@qq.com
 * @time 2017-01-12
 *
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class,
		JpaRepositoriesAutoConfiguration.class,MultipartAutoConfiguration.class })
public class StartApplication extends SpringBootServletInitializer {
	private final static Logger logger = LoggerFactory.getLogger(StartApplication.class);

	public static void main(String[] args) {
		logger.info("logback {}", "INFO ( TRACE < DEBUG < INFO < WARN < ERROR )");
		SpringApplication.run(new Object[] { StartApplication.class }, args);
	}
}
