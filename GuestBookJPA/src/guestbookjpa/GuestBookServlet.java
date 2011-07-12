package guestbookjpa;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.utils.SystemProperty;

@SuppressWarnings("serial")
public class GuestBookServlet extends HttpServlet {
	
	private final static Logger log= Logger.getLogger(GuestBookServlet.class.getName()); 
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
		UserService userService= UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		
		resp.getWriter().println(SystemProperty.environment.value());
		
		String param;
		
		param = getServletConfig().getInitParameter("teamColor");
		
		if(param !=null)
		{
			resp.getWriter().println("Team color is"+ param );
		}
		
		resp.getWriter().println( req.getPathInfo() );
		
		if(user!=null)
		{
			  resp.getWriter().println("Hello, " + user.getNickname());
			
		}
		//else
		//{
		//	resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
			
		//}
		
	}
}
