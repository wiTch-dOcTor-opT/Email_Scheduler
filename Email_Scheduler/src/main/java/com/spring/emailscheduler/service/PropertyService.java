package com.spring.emailscheduler.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.emailscheduler.model.AppProperties;

@Service("propertyService")
public class PropertyService {

	@Autowired
	private JsonReadWriter jsonReaderWriter;

	public String sendPropertyTable() {
		String table;
		AppProperties property = jsonReaderWriter.readProperties();
		table = "<tbody><th width='5%'>Mailer First Name</th><th width='10%'>Set who is sending the mail</th><th width='5%'>Sender's CC Mail</th><th width='5%'>Set your DL Email</th>";
		if (property != null) {
			table += "<tr><td contenteditable='true' class='mailer_name'>"
					+ property.getMailerName() + "</td>";
			table += "<td contenteditable='true' class='from_email'>" + property.getFromEmail() + "</td>";
			table += "<td contenteditable='true' class='sender_email'>" + property.getSenderCCMail() + "</td>";
			table += "<td contenteditable='true' class='dl_email'>" + property.getDlEmail() + "</td>";
		}
		return table + "</tbody>";

	}
	
	public String writeAndSendPropertyTable(AppProperties appPropertiesFromWeb)
	{
		String table;
		table = "<tbody><th width='5%'>Mailer First Name</th><th width='10%'>Set who is sending the mail</th><th width='5%'>Sender's CC Mail</th><th width='5%'>Set your DL Email</th>";
		jsonReaderWriter.writeAppProperties(appPropertiesFromWeb);
		AppProperties property = jsonReaderWriter.readProperties();
		if (property != null) {
			table += "<tr><td contenteditable='true' class='mailer_name'>"
					+ property.getMailerName() + "</td>";
			table += "<td contenteditable='true' class='from_email'>" + property.getFromEmail() + "</td>";
			table += "<td contenteditable='true' class='sender_email'>" + property.getSenderCCMail() + "</td>";
			table += "<td contenteditable='true' class='dl_email'>" + property.getDlEmail() + "</td>";
		}
		return table + "</tbody>";
	}

}
