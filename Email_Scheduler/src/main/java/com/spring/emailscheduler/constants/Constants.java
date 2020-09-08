package com.spring.emailscheduler.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class Constants {

	@Value("${emailapp.jsonsrcfile}")
	private String srcJsonFile;

	@Value("${emailapp.birthdaysrcfile}")
	private String srcBirthdayImg;

	@Value("${emailapp.emailsubject}")
	private String emailSubject;

	@Value("${emailapp.propertiessrcfile}")
	private String appProperties;

	public String getSrcJsonFile() {
		return srcJsonFile;
	}

	public void setSrcJsonFile(String srcJsonFile) {
		this.srcJsonFile = srcJsonFile;
	}

	public String getSrcBirthdayImg() {
		return srcBirthdayImg;
	}

	public void setSrcBirthdayImg(String srcBirthdayImg) {
		this.srcBirthdayImg = srcBirthdayImg;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getAppProperties() {
		return appProperties;
	}

	public void setAppProperties(String appProperties) {
		this.appProperties = appProperties;
	}

}
