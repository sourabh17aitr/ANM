package com.acheron.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;

//@PropertySource("${ACHERON_EMAIL_MODULE_PROPERTY_CONFIG}")
@Configuration
public class ThymeleafConfig {
//	@Autowired
//	private ThymeleafDatabaseResourceResolver thymeleafDatabaseResourceResolver;

	@Bean
	public SpringTemplateEngine templateEngine() {
		return new SpringTemplateEngine();
	}

	@Autowired
	public SpringTemplateEngine templateEngine;

	@Bean
	public TemplateEngine emailTemplateEngine() {
		// final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		// templateEngine.addTemplateResolver(templateResolver());
//		templateEngine.addTemplateResolver(thymeleafDatabaseResourceResolver);
		// templateEngine.addTemplateResolver(fileTemplateResolver());
		return templateEngine;
	}

	// public ITemplateResolver fileTemplateResolver() {
	// FileTemplateResolver templateResolver = new FileTemplateResolver();
	// templateResolver.setPrefix("D:\\templates\\");
	// templateResolver.setSuffix(".html");
	// templateResolver.setTemplateMode("HTML5");
	// templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
	// templateResolver.setOrder(Integer.valueOf(2));
	// templateResolver.setCacheable(false);
	// return templateResolver;
	// }

	// public ITemplateResolver templateResolver() {
	// ClassLoaderTemplateResolver templateResolver = new
	// ClassLoaderTemplateResolver();
	// templateResolver.setPrefix("templates/");
	// templateResolver.setCacheable(false);
	// templateResolver.setSuffix(".html");
	// templateResolver.setTemplateMode("HTML5");
	// templateResolver.setOrder(Integer.valueOf(1));
	// return templateResolver;
	// }

	// public ITemplateResolver databaseTemplateResolver() {
	// ThymeleafDatabaseResourceResolver thymeleafDatabaseResourceResolver = new
	// ThymeleafDatabaseResourceResolver();
	//
	// return thymeleafDatabaseResourceResolver;
	// }

}
