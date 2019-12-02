package com.naresh.auth.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SMTPUtil {

	@Autowired
	private JavaMailSender javaMailSender;

	private static final Logger LOGGER = LoggerFactory.getLogger(SMTPUtil.class);

	public void sendEmail(final String fileName, final String subject, final String from, final String[] to,
			final String body) throws IOException {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);

		msg.setSubject(subject);
		msg.setText(body);

		LOGGER.info("Attempting to send email through SMTP.");
		javaMailSender.send(msg);
		LOGGER.info("Email was sent successfully.");
	}

}
