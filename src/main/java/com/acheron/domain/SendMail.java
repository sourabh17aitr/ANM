package com.acheron.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMail implements Serializable {
	Sender sender;
	Reciever reciever;
	Content content;
	List<MultipartFile> mailAttachments;
	String status;
}
