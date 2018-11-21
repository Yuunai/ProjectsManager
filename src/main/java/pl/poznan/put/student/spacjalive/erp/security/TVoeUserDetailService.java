package pl.poznan.put.student.spacjalive.erp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.UserRepository;
import pl.poznan.put.student.spacjalive.erp.entity.AdministrativeRole;
import pl.poznan.put.student.spacjalive.erp.entity.Privilege;
import pl.poznan.put.student.spacjalive.erp.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
@Transactional
public class TVoeUserDetailService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.getUserByEmail(email);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user
				.isEnabled(), true, true, true, getAuthorities(user.getAdmRoles()));
	}
	
	
	private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<AdministrativeRole> admRoles) {
		return getPrivileges(admRoles).stream().map(e -> new SimpleGrantedAuthority(e)).collect(Collectors.toList());
	}
	
	private final List<String> getPrivileges(final Collection<AdministrativeRole> admRoles) {
		List<Privilege> privileges = new ArrayList<>();
		for (final AdministrativeRole admRole : admRoles) {
			privileges.addAll(admRole.getPrivileges());
		}
		
		return privileges.stream().map(Privilege::getLabel).collect(Collectors.toList());
	}
}
