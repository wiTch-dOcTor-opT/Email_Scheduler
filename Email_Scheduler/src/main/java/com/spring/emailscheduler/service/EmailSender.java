package com.spring.emailscheduler.service;

import java.io.File;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import com.spring.emailscheduler.model.AppProperties;

@Service("emailSender")
public class EmailSender {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private JsonReadWriter jsonReaderWriter;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(EmailSender.class);
	
	public void sendMailWithInlineResources(String to, String name, String subject, String fileToAttach) 
	{
		AppProperties properties = jsonReaderWriter.readProperties();
	    MimeMessagePreparator preparator = new MimeMessagePreparator() 
	    {
	        public void prepare(MimeMessage mimeMessage) throws Exception 
	        {
	            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	            mimeMessage.setFrom(new InternetAddress(properties.getFromEmail()));
	            mimeMessage.setSubject(subject);
	            mimeMessage.addRecipient(RecipientType.CC, new InternetAddress(properties.getDlEmail()));
	            mimeMessage.addRecipient(RecipientType.CC, new InternetAddress(properties.getSenderCCMail()));
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	            
	            String htmlText = "<html>" + 
	            		"  <body>" + 
	            		"    <h1 style='color:red;font-family: Courier;padding-left: 50px;'>" + 
	            		"      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Happy Birthday " + name + "!" +
	            		"    </h1>" + 
	            		//"    <img src='cid:birthdayimage'>" + 
	            		"	 <p>Regards </br> " + properties.getMailerName() +" </br></p>" +
	            		"  </body>" + 
	            		"</html>";
	            helper.setText(htmlText, true);
	             
	            //FileSystemResource res = new FileSystemResource(new File(fileToAttach));
	           // helper.addInline("birthdayimage", res);
	        }
	    };
	     
	    try {
	        mailSender.send(preparator);
	        LOGGER.info("Mail has been successfully sent for " + name + "'s birthday.");
	    }
	    catch (MailException ex) {
	        LOGGER.error("Error while sending mail for + " + name + ". Reason: " + ExceptionUtils.getStackTrace(ex));
	    }
	}	

}
