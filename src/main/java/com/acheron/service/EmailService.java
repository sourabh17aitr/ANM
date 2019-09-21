package com.acheron.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.acheron.domain.SendMail;


@Service
public class EmailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment env;
	@Autowired
	private MailContentBuilder mailContent;
	@Autowired
	private UtilService utilservice;

	
	public void sendMail(SendMail sendEmail) throws MessagingException, UnsupportedEncodingException {
		String[] toUsers = new String[sendEmail.getReciever().getTo().size()];
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setTo(sendEmail.getReciever().getTo().toArray(toUsers));
		if (sendEmail.getReciever().getCc() != null && !sendEmail.getReciever().getCc().isEmpty()) {
			String[] ccUsers = new String[sendEmail.getReciever().getCc().size()];
			mimeMessageHelper.setCc(sendEmail.getReciever().getCc().toArray(ccUsers));
		}
		if (sendEmail.getReciever().getBcc() != null && !sendEmail.getReciever().getBcc().isEmpty()) {
			String[] bccUsers = new String[sendEmail.getReciever().getBcc().size()];
			mimeMessageHelper.setBcc(sendEmail.getReciever().getBcc().toArray(bccUsers));
		}

		if (sendEmail.getSender() == null || utilservice.isNullorEmpty(sendEmail.getSender().getEmailId())) {
			mimeMessageHelper.setFrom(env.getProperty("spring.mail.username"));
			mimeMessageHelper.setReplyTo(env.getProperty("spring.mail.username"));
		} else {
			mimeMessageHelper.setFrom(sendEmail.getSender().getEmailId(), sendEmail.getSender().getUserId());
			mimeMessageHelper.setReplyTo(sendEmail.getSender().getEmailId());
		}
		mimeMessageHelper.setSubject(sendEmail.getContent().getSubject());

		mimeMessageHelper
				.setText(mailContent.build(formNotificationContent(sendEmail.getContent().getTemplateContent()),
						sendEmail.getContent().getTemplateName()), true);
		addMultipleAttachments(mimeMessageHelper,
				utilservice.convertMultipartFileToFile(sendEmail.getMailAttachments()));
		mailSender.send(mimeMessage);
	}

	public Map<String, Object> formNotificationContent(List<com.acheron.domain.TemplateContent> list) {
		if (list != null && !list.isEmpty()) {
			Map<String, Object> notificationContent = new HashMap<>();
			list.forEach(content -> {
				notificationContent.put(content.getAttribute(), content.getValue());
			});
			return notificationContent;
		}
		return null;
	}

	public void addMultipleAttachments(MimeMessageHelper mimeMessageHelper, List<File> fileObjects) {
		if (mimeMessageHelper != null && !fileObjects.isEmpty()) {
			for (File fileObject : fileObjects) {
				try {
					mimeMessageHelper.addAttachment(fileObject.getName(), fileObject);
				} catch (MessagingException e) {
					logger.error("Failed while adding attachment with exception " + e);
				}
			}
		}
	}
}
