package com.acheron.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Content {
	private String subject;
	private String templateName;
	List<TemplateContent> templateContent;
}
