package com.acheron.controller;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.acheron.domain.Slack;

@RestController
public class SlackController {

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/sendSlack", method = RequestMethod.POST)
	private @ResponseBody String sendSlack(@RequestBody Slack slack) {
		if (slack == null) {
			throw new IllegalArgumentException("Mandatory parameter missing for sending slack notification");
		}

		System.out.println(slack.toString());

		String slackUrl = slack.getSlackUrl();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		json.put("text", slack.getMessage());

		HttpEntity<String> httpEntity = new HttpEntity<String>(json.toString(), httpHeaders);

		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.postForObject(slackUrl, httpEntity, String.class);

		return response;
	}
}
