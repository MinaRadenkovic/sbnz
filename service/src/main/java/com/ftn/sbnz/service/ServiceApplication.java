package com.ftn.sbnz.service;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.internal.io.ResourceFactory;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ServiceApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieFileSystem kfs = ks.newKieFileSystem();
		kfs.write(ResourceFactory.newClassPathResource("META-INF/kmodule.xml"));
		kfs.write(ResourceFactory.newClassPathResource("rules/transaction-rules.drl"));

		KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();
		Results results = kieBuilder.getResults();
		if (results.hasMessages(Message.Level.ERROR)) {
			throw new IllegalStateException("KIE build errors: " + results.getMessages());
		}

		return ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
	}
}
