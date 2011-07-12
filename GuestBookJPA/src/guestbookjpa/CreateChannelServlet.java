package guestbookjpa;

import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

@SuppressWarnings("serial")
public class CreateChannelServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String userid = req.getParameter("userid");
		if (userid != null) {
			ChannelService channelService = ChannelServiceFactory
					.getChannelService();

			String token = channelService.createChannel(userid);

			// to generate the channel page
			FileReader reader = new FileReader("channel-template");
			CharBuffer buffer = CharBuffer.allocate(16384);
			reader.read(buffer);
			String index = new String(buffer.array());

			index = index.replaceAll("\\{\\{ me \\}\\}", userid);
			index = index.replaceAll("\\{\\{ token \\}\\}", token);
			
			resp.setContentType("text/html");
			resp.getWriter().write(index);
			    
		    
		} else {
			resp.setContentType("text/plain");
			resp.getWriter().println("You need a userid to create a channel");

		}
	}

}
