package com.hendall.survey.ui.filter;

import java.io.IOException;
import java.net.HttpRetryException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hendall.survey.services.datamodel.UserSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
this.context = fConfig.getServletContext();
this.context.log("AuthenticationFilter initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException {

HttpServletRequest req = (HttpServletRequest) request;
HttpServletResponse res = (HttpServletResponse) response;

String uri = req.getRequestURI();
this.context.log("Requested Resource::" + uri);

HttpSession session = req.getSession(false);
UserSession userSession = null;
if (session != null) {
	userSession = (UserSession) session.getAttribute("userSession");
}

if (userSession == null) {
	this.context.log("Unauthorized access request");
	res.sendRedirect("/Survey-web/login.jsf");
} else {
	// pass the request along the filter chain
	chain.doFilter(request, response);
}

	}

	public void destroy() {
// close any resources here
	}

}