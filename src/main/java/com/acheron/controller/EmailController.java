package com.acheron.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acheron.domain.Content;
import com.acheron.domain.Reciever;
import com.acheron.domain.SendMail;
import com.acheron.domain.Status;
import com.acheron.service.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	private @ResponseBody String test() {
		return "success";
	}

	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	private @ResponseBody SendMail sendEmail(@RequestBody SendMail sendEmail, Model model) {
		if (sendEmail == null) {
			throw new IllegalArgumentException("Mandatory parameter missing for sending email");
		}
		try {
			emailService.sendMail(sendEmail);
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new InternalError("Failed while sending email with error " + e);
		}
		sendEmail.setStatus(Status.SUCCESS.getValue());
		return sendEmail;
	}
	
	@RequestMapping(value = "/sendTestMail", method = RequestMethod.GET)
	private @ResponseBody SendMail sendTestEmail() throws UnsupportedEncodingException, MessagingException {
		SendMail sendMail = new SendMail();
		Reciever reciever = new Reciever();
		List<String> sender = new ArrayList();
		sender.add("sourabh@acheron-tech.com");
		reciever.setTo(sender);
		sendMail.setReciever(reciever);
		Content mailContent = new Content();
		mailContent
				.setSubject("ANM mail");
		mailContent.setTemplateName("Hi,");
		sendMail.setContent(mailContent);
		emailService.sendMail(sendMail);
		return sendMail;
	
	}
}
