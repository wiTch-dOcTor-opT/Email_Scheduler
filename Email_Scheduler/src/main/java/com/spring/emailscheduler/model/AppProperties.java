package com.spring.emailscheduler.model;

public class AppProperties {

	private String mailerName;
	private String fromEmail;
	private String senderCCMail;
	private String dlEmail;

	public String getMailerName() {
		return mailerName;
	}

	public void setMailerName(String mailerName) {
		this.mailerName = mailerName;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getDlEmail() {
		return dlEmail;
	}

	public void setDlEmail(String dlEmail) {
		this.dlEmail = dlEmail;
	}

	public String getSenderCCMail() {
		return senderCCMail;
	}

	public void setSenderCCMail(String senderCCMail) {
		this.senderCCMail = senderCCMail;
	}
	

}
