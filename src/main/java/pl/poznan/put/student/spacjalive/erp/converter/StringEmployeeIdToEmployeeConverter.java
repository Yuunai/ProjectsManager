package pl.poznan.put.student.spacjalive.erp.converter;

import org.springframework.core.convert.converter.Converter;
import pl.poznan.put.student.spacjalive.erp.entity.User;
import pl.poznan.put.student.spacjalive.erp.service.UserService;

public class StringEmployeeIdToEmployeeConverter implements Converter<String, User> {
	
	private final UserService userService;
	
	public StringEmployeeIdToEmployeeConverter(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public User convert(String s) {
		User user;
		user = userService.getUser(Integer.valueOf(s));
		
		return user;
	}
}
