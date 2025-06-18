package de.fi.webapp.mail;

public class MailConnector {
    private String host;
    private String username;
    private String password;
    private String protocol;

    public MailConnector(final String host, final String username, final String password, final String protocol) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.protocol = protocol;
    }

    public void send() {
        System.out.printf("Sending email... %s %s %s %s\n", host, username, password, protocol);
    }
}
