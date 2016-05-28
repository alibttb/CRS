package me.bttb.crs.beans.user;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements  Filter {

	private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<partial-response><redirect url=\"%s\"></redirect></partial-response>";

	/**
	 * Checks if user is logged in. If not it redirects to the login.xhtml page.
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// Get the loginBean from session attribute
		UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");
		UsersBean usersBean = (UsersBean) request.getSession().getAttribute("usersBean");

		// HttpSession session = request.getSession(false);
		String loginURL = request.getContextPath() + "/login-page.xhtml";
		String indexURl = request.getContextPath() + "/index.xhtml";
		String firstUserURl = request.getContextPath() + "/first-user.xhtml";
//		String reqUri = request.getRequestURI();
		boolean loggedIn = (userBean != null) && (userBean.isAuthenticated());
		boolean loginRequest = request.getRequestURI().equals(loginURL);
		boolean indexRequset = request.getRequestURI().equals(indexURl);
		boolean firstUser = request.getRequestURI().equals(firstUserURl);
		if (usersBean != null) {
			firstUser = firstUser && usersBean.getUsersCount() == 0;
		} else {
			firstUser = false;
		}
		boolean resourceRequest = request.getRequestURI()
				.startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
		boolean ajaxRequest = "partial/ajax".equals(request.getHeader("Faces-Request"));

		if (loggedIn || loginRequest || resourceRequest || indexRequset || firstUser) {
			if (!resourceRequest) { // Prevent browser from caching restricted
									// resources. See also
									// http://stackoverflow.com/q/4194207/157882
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
																							// 1.1.
				response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				response.setDateHeader("Expires", 0); // Proxies.
			}
			chain.doFilter(request, response); // So, just continue request.
		} else if (ajaxRequest) {
			response.setContentType("text/xml");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().printf(AJAX_REDIRECT_XML, loginURL);
		} else {
			response.sendRedirect(indexURl);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		// Nothing to do here!
	}

	public void destroy() {
		// Nothing to do here!
	}

}