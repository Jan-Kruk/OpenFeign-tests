package com.proj.project1;

import feign.FeignException;
import feign.RetryableException;
import feign.codec.Decoder;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.ApplicationStartupAware;
import org.springframework.context.event.EventListener;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@SpringBootApplication
@EnableFeignClients
public class Project1Application {

	@Autowired
	ShawnMendesProxy shawnMendesClient;

	Logger log = getLogger(Project1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}

	@EventListener(ApplicationStartedEvent.class)
	public void makeRequestToShawnMendesEndpoint(){
		try {
			ShawnMendesResponse response = shawnMendesClient.makeSearchRequest("shawnmendes", 6);
			List<ShawnMendesResult> results = response.results();
			results.forEach(shawnMendesResult -> System.out.println(shawnMendesResult.trackName()));
		}catch (FeignException.FeignClientException e){
//			System.out.println("feign client exception");
//			System.out.println(e.status());
//			System.out.println(e.getMessage());
			log.error("client esception: " + e.status());
		}catch (RetryableException e){
			System.out.println("Feign server exception");
			System.out.println(e.status());
			System.out.println(e.getMessage());
		} catch (FeignException e){
			System.out.println(e.getMessage());
			System.out.println(e.status());
		}
	}

}
