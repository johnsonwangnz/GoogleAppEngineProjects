package guestbookjpa.task;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.appengine.api.labs.taskqueue.TaskOptions;

@SuppressWarnings("serial")
public class CreateTaskServlet extends HttpServlet {
	
	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		String strCallResult="";
		
		resp.setContentType("text/plain");
		
		try
		{
			String strEmailId= req.getParameter("emailid");
			if(strEmailId==null) 
				throw new Exception("Email Id field cannot be empty.");
			
			Queue queue = QueueFactory.getQueue("subscription-queue");
			
			queue.add(TaskOptions.Builder.url("/signupsubscriber").
					param("emailid", strEmailId));
			
			strCallResult = "Successfully created a Task in the Queue";
			
			resp.getWriter().println(strCallResult);
		}
		catch (Exception ex)
		{
			strCallResult = ex.getMessage();
			resp.getWriter().println(strCallResult);
		}
	}
	
}
