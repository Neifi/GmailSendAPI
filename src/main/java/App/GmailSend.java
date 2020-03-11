package App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

@ComponentScan(basePackages  = {"mailsend.MailSenderrrr","Controller"})
public class GmailSend extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(GmailSend.class, args);
	}
}
