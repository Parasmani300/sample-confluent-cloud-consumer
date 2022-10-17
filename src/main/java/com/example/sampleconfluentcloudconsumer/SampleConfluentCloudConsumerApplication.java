package com.example.sampleconfluentcloudconsumer;

import com.example.sampleconfluentcloudconsumer.config.RootConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RootConfig.class})
public class SampleConfluentCloudConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleConfluentCloudConsumerApplication.class, args);
	}

}
