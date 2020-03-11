package mailsend;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

@Service

public class MailSenderrrr {

	/**
	 * Create a MimeMessage using the parameters provided.
	 *
	 * @param to       email address of the receiver
	 * @param from     email address of the sender, the mailbox account
	 * @param subject  subject of the email
	 * @param bodyText body text of the email
	 * @return the MimeMessage to be used to send email
	 * @throws MessagingException
	 */
	public static MimeMessage createEmail(String to, String from, String subject, String title, String bodyText,
			String link, String footer, String charset, String linkText) throws MessagingException {

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Multipart multiPart = new MimeMultipart("alternative");
		MimeMessage email = new MimeMessage(session);

		// set html to msg
		MimeBodyPart h1Tag = new MimeBodyPart();
		MimeBodyPart pBodyTag = new MimeBodyPart();
		MimeBodyPart aTagHref = new MimeBodyPart();
		MimeBodyPart pFooterTag = new MimeBodyPart();

		h1Tag.setContent("<h1>" + title + "</h1>", charset);
		pBodyTag.setContent("<p>" + bodyText + "</p>", charset);
		aTagHref.setContent("<a href=" + link + ">" + "linkText" + "</a>", charset);
		pFooterTag.setContent("<p>" + footer + "</p>", charset);

		email.setFrom(new InternetAddress(from));
		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
		email.setSubject(subject);

		email.setContent(multiPart);

		return email;
	}

	/**
	 * Create a Message from an email
	 *
	 * @param email Email to be set to raw of message
	 * @return Message containing base64url encoded email.
	 * @throws IOException
	 * @throws MessagingException
	 */
	public static Message createMessageWithEmail(MimeMessage email) throws MessagingException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		email.writeTo(baos);
		String encodedEmail = Base64.encodeBase64URLSafeString(baos.toByteArray());
		Message message = new Message();
		message.setRaw(encodedEmail);
		return message;
	}
	
	
	public static void sendMessage(Gmail service, String userId, MimeMessage email)
			throws MessagingException, IOException {
		Message message = createMessageWithEmail(email);
		message = service.users().messages().send(userId, message).execute();

		System.out.println("Message id: " + message.getId());
		System.out.println(message.toPrettyString());
	}

}
