package controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.gmail.Gmail;

import authentication.Authentication;
import mailsend.MailSenderrrr;

@RestController
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class Controller {

	
	@RequestMapping("/")
	public String homePage() {
		
		return "home";
	}
	
	@RequestMapping("/send")
	public String enviar() {
		try {
			Gmail service = Authentication.buildService();
			MimeMessage mail = MailSenderrrr.createEmail("gestiongymapp@gmail.com", "reytax96@gmail.com", "TestAPI", "Title", "Body", "#",
					"footer", "charset=utf-8", "linkText");
			MailSenderrrr.createMessageWithEmail(mail);
			MailSenderrrr.sendMessage(service,"me",mail);
			return "enviado";
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "enviado";
	}
}
