package pl.poznan.put.student.spacjalive.erp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import pl.poznan.put.student.spacjalive.erp.entity.User;
import pl.poznan.put.student.spacjalive.erp.service.UserService;

public class TVoeAuthenticationProvider extends DaoAuthenticationProvider {
	
	@Autowired
	UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		User user = userService.getUserByEmail(authentication.getName());
		if(user == null)
			throw new BadCredentialsException("Invalid username or password");
		
		final Authentication result = super.authenticate(authentication);
		return new UsernamePasswordAuthenticationToken(user, result.getCredentials());
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
	}
}
