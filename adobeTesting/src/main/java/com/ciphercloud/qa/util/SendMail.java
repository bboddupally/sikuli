package com.ciphercloud.qa.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

	private final FileInputStream fis = null;

	public void sendMail(String to, String subject, String mailbody) {
		try {
			/*
			 * String emailPropsPath = TenantConfigHelper.getTenantDirectory() +
			 * File.separator + "adminconsoleEmail.properties"; Properties
			 * emailProps = new Properties(); fis = new FileInputStream(new
			 * File(emailPropsPath)); emailProps.load(fis);
			 */
			String user = "adminqa@ciphercloud.com";
			String password = "ciphercloud@123";
			String smtpStarttlsRequired = "true";
			String smtpPort = "587";
			String smtpAuth = "true";
			String smtpHost = "smtp.gmail.com";
			String smtpStarttlsEnable = "true";
			/*
			 * String decryptedPassword = null; String masterPassword =
			 * PasswordUtil.deobfuscate(ServerSecurity .getServerPassword());
			 *
			 * String[] digestVsEncrypted = password.split("::"); if
			 * (digestVsEncrypted.length == 2) { String key =
			 * PasswordUtil.digest(masterPassword, digestVsEncrypted[0]);
			 * password = new String(Base64.decodeBase64(digestVsEncrypted[1]));
			 * decryptedPassword = PasswordUtil.decrypt(key, password); } else {
			 * decryptedPassword = password; }
			 */

			Properties props = new Properties();
			props.setProperty("mail.smtp.starttls.required",
					smtpStarttlsRequired);
			props.setProperty("mail.host", smtpHost);
			props.setProperty("mail.smtp.port", smtpPort);
			props.setProperty("mail.smtp.auth", smtpAuth);
			props.setProperty("mail.smtp.starttls.enable", smtpStarttlsEnable);

			Authenticator auth = new SMTPAuthenticator(user, password);

			Session session = Session.getInstance(props, auth);

			MimeMessage msg = new MimeMessage(session);
			// msg.setText(message);
			msg.setSubject(subject);
			// msg.setFrom(new InternetAddress(login));
			// msg.setSender(new InternetAddress(login));
			msg.addRecipients(Message.RecipientType.TO, to);

			MimeMultipart multipart = new MimeMultipart("related");

			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = mailbody;
			messageBodyPart.setContent(htmlText, "text/html");

			// add it
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);
			Transport.send(msg);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(
					"Exception while processing with send mail " + ex, ex);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException ignore) {
					// ignore
				}
			}
		}
	}

	private class SMTPAuthenticator extends Authenticator {

		private final PasswordAuthentication authentication;

		public SMTPAuthenticator(String login, String password) {
			authentication = new PasswordAuthentication(login, password);
		}

		
		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}

	}

	/*public static void main(String[] arg) {
		String str = "We received some spam messages from your account. If it is not you, kindly change your password.\n\n\nDo not replay to this auto genereated mail.";
		new SendMail().sendMail("admin@ciphercloud.in", "Subject of EmailInbound", "Body of EmailInbound");
		System.out.println("mail sent successfull");
	}*/

}
