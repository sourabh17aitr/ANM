package com.acheron.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UtilService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Boolean isNullorEmpty(String value) {
		if ((value != null) && (!"".equals(value))) {
			return Boolean.valueOf(false);
		}
		return Boolean.valueOf(true);
	}

	public List<File> convertMultipartFileToFile(List<MultipartFile> multipartFile) {
		List<File> fileList = new ArrayList<File>();
		if (multipartFile != null && !multipartFile.isEmpty()) {
			multipartFile.forEach(file -> {
				File convFileObject = new File(file.getOriginalFilename());
				try {
					file.transferTo(convFileObject);
					fileList.add(convFileObject);
				} catch (IllegalStateException | IOException e) {
					logger.error("Failed while converting the file with exception " + e);
				}
			});
		}
		return fileList;
	}

}
