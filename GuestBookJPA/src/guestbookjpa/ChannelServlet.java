package guestbookjpa;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.repackaged.org.json.JSONObject;

public class ChannelServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String fromUser = req.getParameter("userid");

		String toUser = req.getParameter("touserid");

		String message = req.getParameter("message");
		if (toUser != "0") {
			sendUpdateToUser(toUser, fromUser, message);
		}
	}

	private void sendUpdateToUser(String touser, String fromuser, String Message) {
		ChannelService channelService = ChannelServiceFactory
				.getChannelService();

		channelService.sendMessage(new ChannelMessage(touser, getMessageString(
				touser, fromuser, Message)));

	}

	public String getMessageString(String touser, String fromuser,
			String Message) {
		Map<String, String> state = new HashMap<String, String>();
		state.put("fromUser", fromuser);
		state.put("toUser", touser);
		state.put("message", Message);

		JSONObject message = new JSONObject(state);
		return message.toString();
	}
}
