package guestbookjpa;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class SignGuestBookServlet extends HttpServlet {

	private static final Logger log = Logger
			.getLogger(SignGuestBookServlet.class.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String content = req.getParameter("content");

		if (content == null) {
			content = "(No greeting)";
		}
		if (user != null) {
			log.info("Greeting posted by user " + user.getNickname() + ": "
					+ content);
		} else {
			log.info("Greeting posted anonymously: " + content);
		}
		resp.sendRedirect("/guestbook.jsp");

		
	}
	
	
}
