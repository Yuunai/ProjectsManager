package pl.poznan.put.student.spacjalive.erp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.poznan.put.student.spacjalive.erp.exceptions.NoAccessGrantedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	
	private static final Logger LOGGER = LogManager.getLogger(GlobalControllerExceptionHandler.class);
	
	public static final String DEFAULT_ERROR_VIEW = "error";
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView
	defaultErrorHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
		LOGGER.error("Exception occurred while handling URL: " + req.getRequestURL(), e);
		
		if (AnnotationUtils.findAnnotation
				(e.getClass(), ResponseStatus.class) != null)
			throw e;
		
		String message;
		if(e instanceof HibernateException || e instanceof DataAccessException) {
			message = "Database error occurred, wait some time, and contact administrator if problem persists.";
			response.setStatus(HttpServletResponse.SC_OK);
		} else if(e instanceof NoAccessGrantedException) {
			message = "Forbidden! No access granted.";
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		} else {
			message = "Internal error occurred, wait some time, and contact administrator if problem persists.";
			response.setStatus(HttpServletResponse.SC_OK);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", message);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}

}
