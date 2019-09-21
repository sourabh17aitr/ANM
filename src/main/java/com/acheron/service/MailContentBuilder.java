package com.acheron.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class MailContentBuilder {
	@Autowired
	private SpringTemplateEngine templateEngine;

	public String build(Map<String, Object> contextMessage, String template) {
		Context context = new Context();
		context.setVariables(contextMessage);
		return templateEngine.process(template, context);
	}
}
