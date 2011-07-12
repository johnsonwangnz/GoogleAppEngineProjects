package guestbookjpa.task;

import guestbookjpa.SignGuestBookServlet;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SignupSubscriberServlet extends HttpServlet {

	private static final Logger log = Logger
	.getLogger(SignupSubscriberServlet.class.getName());
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String strCallResult="";
		resp.setContentType("text/plain");
		try
		{
			String strEmailId = req.getParameter("emailid");
			
			// send an email to subscriber
			Properties prop  = new Properties();
			Session session = Session.getDefaultInstance(prop);
			
			String msgBody="This is from google app engine task manager...";
			
			log.warning(strEmailId +" has been subscribed and message should be sent to");
			try
			{
				Message message =new MimeMessage(session);
				message.setFrom(new InternetAddress("johnsonwangnz@gmail.com","Johnson Wang GAE"));
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(strEmailId,"Guest"));
				message.setSubject("Your Example.com account has been activated");
		        message.setText(msgBody);
		       Transport.send(message);
		       
		            
			}
			catch (AddressException ae)
			{
				strCallResult = ae.getMessage();
				log.severe(strCallResult);  
				resp.getWriter().println(strCallResult);
			}
			catch (MessagingException me) 
			{
				strCallResult = me.getMessage();
				log.severe(strCallResult);  
				resp.getWriter().println(strCallResult);
				
			}
			
		}
		catch (Exception ex)
		{
			strCallResult = ex.getMessage();
			log.severe(strCallResult);  
			resp.getWriter().println(strCallResult);
			
		}
		
	}
}
