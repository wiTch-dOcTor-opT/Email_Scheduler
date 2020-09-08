package com.spring.emailscheduler.scheduler;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spring.emailscheduler.constants.Constants;
import com.spring.emailscheduler.model.Employee;
import com.spring.emailscheduler.service.EmailSender;

@Component
@PropertySource("classpath:application.properties")
public class MailScheduler {

	@Autowired
    private EmailSender emailService;
	
	@Autowired
    private Constants constants;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(MailScheduler.class);
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	
	@Scheduled(fixedRate = 90000)
	//@Scheduled(cron = "${cron.expression}")
	public void scheduleEmail() 
	{
		LOGGER.info("Starting scheduler to check the birthday list for sending mails.");
		Gson gson =new Gson();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			Type empListType = new TypeToken<ArrayList<Employee>>(){}.getType();
			List<Employee> empList = gson.fromJson(new FileReader(constants.getSrcJsonFile()), empListType); 
			
			for(Employee emp : empList) {
				Date birthdate = simpleDateFormat.parse(emp.getBirthdate());
				Date today = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
				if(birthdate.compareTo(today) == 0) {
					LOGGER.info(emp.getName() + " has birthday today. Sending email to the team.");
					emailService.sendMailWithInlineResources(emp.getEmail(),emp.getName(), constants.getEmailSubject() + emp.getName(), 
							constants.getSrcBirthdayImg() + ThreadLocalRandom.current().nextInt(1, 4)  + ".jpg");
				}
			}
			
			
		} catch (Exception e) {
			LOGGER.error("Error while sending mail. Reason: "+ ExceptionUtils.getStackTrace(e));
		}
	}
}
