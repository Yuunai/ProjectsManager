package pl.poznan.put.student.spacjalive.erp.converter;

import org.springframework.core.convert.converter.Converter;
import pl.poznan.put.student.spacjalive.erp.entity.AdministrativeRole;
import pl.poznan.put.student.spacjalive.erp.service.UserService;

public class StringAdmRoleIdToAdmRoleConverter implements Converter<String, AdministrativeRole> {
	
	private final UserService userService;
	
	public StringAdmRoleIdToAdmRoleConverter(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public AdministrativeRole convert(String s) {
		return userService.getAdmRole(Integer.valueOf(s));
	}
}
