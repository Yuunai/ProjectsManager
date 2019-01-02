package pl.poznan.put.student.spacjalive.erp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.UserRepository;
import pl.poznan.put.student.spacjalive.erp.entity.AdministrativeRole;
import pl.poznan.put.student.spacjalive.erp.entity.User;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

@Service("userDetailsService")
@Transactional
public class TVoeUserDetailService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user;
		try {
			user = userRepository.getUserByEmail(email);
		} catch (NotFoundException e) {
			throw new UsernameNotFoundException("Username: " + email + "not found!");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user
				.isEnabled(), true, true, true, getAuthorities(user.getAdmRoles()));
	}
	
	
	private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<AdministrativeRole> admRoles) {
		return admRoles.stream().map(e -> new SimpleGrantedAuthority(e.getRole())).collect(Collectors.toList());
	}
}
