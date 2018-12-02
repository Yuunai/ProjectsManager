package pl.poznan.put.student.spacjalive.erp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.poznan.put.student.spacjalive.erp.entity.User;
import pl.poznan.put.student.spacjalive.erp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class TVoeSimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse
			httpServletResponse, Authentication authentication) throws IOException, ServletException {
		handle(httpServletRequest, httpServletResponse, authentication);
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
	
	protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication
			authentication) throws IOException {
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
