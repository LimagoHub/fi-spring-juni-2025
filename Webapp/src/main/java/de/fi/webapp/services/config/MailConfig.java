package de.fi.webapp.services.config;

import de.fi.webapp.YamlPropertySourceFactory;
import de.fi.webapp.mail.MailConnector;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value= "classpath:mail.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "mail")
@Setter
public class MailConfig {
    private String host;
    private String username;
    private String password;
    private String protocol;

    @Bean
    public MailConnector createConnector() {
        return new MailConnector(host, username, password, protocol);
    }
}
