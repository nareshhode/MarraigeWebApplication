package com.naresh.auth.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.naresh.auth.model.SMTPInfo;

//@Configuration
//@PropertySources(value = {@PropertySource("classpath:smtp/smtp.properties")})
public class SMTPConfig {

    @Value("${mail.protocol}")
    private String protocol;

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.smtp.socketFactory.port}")
    private int socketPort;

    @Value("${mail.smtp.auth}")
    private boolean auth;

    @Value("${mail.smtp.starttls.enable}")
    private boolean starttls;

    @Value("${mail.smtp.starttls.required}")
    private boolean starttlsRequired;

    @Value("${mail.smtp.debug}")
    private boolean debug;

    @Value("${mail.smtp.socketFactory.fallback}")
    private boolean fallback;

    @Bean
    public SMTPInfo getSMTPInfo() throws IOException {
    	SMTPInfo smtpInfo = new SMTPInfo();
    	smtpInfo.setProtocol(protocol);
    	//smtpInfo.setHost(host);
    	smtpInfo.setPort(port);
    	smtpInfo.setSocketPort(socketPort);
    	smtpInfo.setAuth(auth);
    	smtpInfo.setStarttls(starttls);
    	smtpInfo.setStarttlsRequired(starttlsRequired);
    	smtpInfo.setFallback(fallback);
        return smtpInfo;
    }
}