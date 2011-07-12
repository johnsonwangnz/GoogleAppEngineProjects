package guestbookjpa;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LogFilterImpl implements Filter {
	
	private FilterConfig  filterConfig;
	
	private static final Logger log = Logger.getLogger(
			LogFilterImpl.class.getName());
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		log.warning("Log filter processed a " + 
				getFilterConfig().getInitParameter("logType"));
		
		filterChain.doFilter(request, response);
	}

	public  FilterConfig getFilterConfig() {
		return filterConfig;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	
	
	
}
