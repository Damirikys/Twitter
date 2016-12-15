package ru.urfu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.net.MalformedURLException;

@SpringBootApplication
public class TwitterApplication extends SpringBootServletInitializer
{

	public static void main(String[] args) throws MalformedURLException
	{
		SpringApplication.run(TwitterApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return builder.sources(TwitterApplication.class);
	}
}