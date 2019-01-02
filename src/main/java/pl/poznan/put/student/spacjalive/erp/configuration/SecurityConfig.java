package pl.poznan.put.student.spacjalive.erp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.poznan.put.student.spacjalive.erp.security.*;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "pl.poznan.put.student.spacjalive.erp.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private TVoeAuthenticationSuccessHandler tvoeAuthenticationSuccessHandler;
	
	@Autowired
	private TVoeLogoutSuccessHandler tvoeLogoutSuccessHandler;
	
	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		TVoeAuthenticationProvider authProvider = new TVoeAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/login*", "/resetPassword*", "/setNewPassword*", "/public").permitAll()
				.antMatchers("/account/**").hasRole("ADMIN")
				.antMatchers("/equipment/list", "/event/list", "/home", "/reservation/list", "/user/list",
						"/event/eventDetails", "/participation/**")
					.hasAnyRole("ADMIN", "MODERATOR", "TRUSTED", "USER")
				.antMatchers("/equipment/add*", "/equipment/updateEquipmentForm",
						"/event/add*", "/event/update*", "/reservation/add*", "/reservation/updateReservationForm")
					.hasAnyRole("ADMIN", "MODERATOR", "TRUSTED")
				.antMatchers("/equipment/deleteEquipment", "/event/deleteEvent", "/reservation/deleteReservation")
					.hasAnyRole("ADMIN", "MODERATOR")
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/loginPage")
				.loginProcessingUrl("/authUser")
				.defaultSuccessUrl("/home")
				.successHandler(tvoeAuthenticationSuccessHandler)
				.permitAll()
			.and()
			.logout()
				.logoutSuccessHandler(tvoeLogoutSuccessHandler)
				.permitAll();
	}
	
}
