package pl.poznan.put.student.projectsmanager.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.poznan.put.student.projectsmanager.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

@Component
public class TVoeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse
			httpServletResponse, Authentication authentication) throws IOException, ServletException {
		handle(httpServletRequest, httpServletResponse);
		HttpSession session = httpServletRequest.getSession(false);
		if (session != null) {
			session.setMaxInactiveInterval(30 * 60);
			
			String username;
			if (authentication.getPrincipal() instanceof User) {
				User user = (User) authentication.getPrincipal();
				username = user.getEmail();
				session.setAttribute("userId", user.getId());
			}
			else {
				username = authentication.getName();
			}
			session.setAttribute("username", username);
		}
		clearAuthenticationAttributes(httpServletRequest);
	}
	
	protected void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, request.getContextPath() + "/home");
	}
	
	protected void clearAuthenticationAttributes(final HttpServletRequest request) {
		final HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
