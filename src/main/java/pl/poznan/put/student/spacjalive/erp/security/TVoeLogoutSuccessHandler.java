package pl.poznan.put.student.spacjalive.erp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

@Component
public class TVoeLogoutSuccessHandler implements LogoutSuccessHandler {
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		final HttpSession session = request.getSession();
		if (session != null) {
			session.removeAttribute("userId");
			session.removeAttribute("username");
		}
		response.sendRedirect("/loginPage?logout");
	}
}
