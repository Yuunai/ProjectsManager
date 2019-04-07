package pl.poznan.put.student.projectsmanager.converter;

import org.springframework.core.convert.converter.Converter;
import pl.poznan.put.student.projectsmanager.entity.User;
import pl.poznan.put.student.projectsmanager.exceptions.NotFoundException;
import pl.poznan.put.student.projectsmanager.service.UserService;

public class StringUserIdToUserConverter implements Converter<String, User> {
	
	private final UserService userService;
	
	public StringUserIdToUserConverter(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public User convert(String s) {
		User user;
		try {
			user = userService.getUser(Integer.valueOf(s));
		} catch (NotFoundException e) {
			user = null;
		}
		
		return user;
	}
}
