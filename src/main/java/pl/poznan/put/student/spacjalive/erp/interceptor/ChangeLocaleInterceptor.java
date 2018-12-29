package pl.poznan.put.student.spacjalive.erp.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.*;

public class ChangeLocaleInterceptor extends HandlerInterceptorAdapter {
	
	private static final String S_LANGUAGE = "language";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String newLocale = request.getParameter("lang");
		HttpSession session = request.getSession();
		if(newLocale != null) {
			if(newLocale.startsWith("pl")) {
				session.setAttribute(S_LANGUAGE, "pl_PL");
			} else if(newLocale.startsWith("en")) {
				session.setAttribute(S_LANGUAGE, "en_EN");
			}
		} else if(session.getAttribute(S_LANGUAGE) == null) {
			session.setAttribute(S_LANGUAGE, request.getLocale());
		}
		
		return true;
	}
}
