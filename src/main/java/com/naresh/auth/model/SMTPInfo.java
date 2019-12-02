package com.naresh.auth.model;


public class SMTPInfo {

    private String protocol;
    private String host;
    private int port;
    private int socketPort;
    private boolean auth;
    private boolean starttls;
    private boolean starttlsRequired;
    private boolean debug;
    private boolean fallback;

    public String getProtocol() {
    	return this.protocol;
    }

    public void setProtocol(final String protocol) {
    	this.protocol = protocol;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public int getSocketPort() {
        return this.socketPort;
    }

    public void setSocketPort(final int socketPort) {
        this.socketPort = socketPort;
    }

    public boolean getAuth() {
    	return this.auth;
    }

    public void setAuth(final boolean auth) {
    	this.auth = auth;
    }

    public boolean getStarttls() {
    	return this.starttls;
    }

    public void setStarttls(final boolean starttls) {
    	this.starttls = starttls;
    }

    public boolean getStarttlsRequired() {
    	return this.starttlsRequired;
    }

    public void setStarttlsRequired(final boolean starttlsRequired) {
    	this.starttlsRequired = starttlsRequired;
    }

    public boolean getDebug() {
    	return this.debug;
    }

    public void setDebug(final boolean debug) {
    	this.debug = debug;
    }

    public boolean getFallback() {
    	return this.fallback;
    }

    public void setFallback(final boolean fallback) {
    	this.fallback = fallback;
    }
}
