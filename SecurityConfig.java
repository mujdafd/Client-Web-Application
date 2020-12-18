package ca.sheridancollege.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ca.sheridancollege.service.UserDetailsServiceImpl;



@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests()

			.antMatchers("/add-contact").hasAnyRole("ADMIN", "MEMBER")
			.antMatchers("/list-contact").hasAnyRole("ADMIN", "MEMBER")
			.antMatchers("/edit-contact/**").hasAnyRole("ADMIN", "MEMBER")
			.antMatchers("/delete-contact/**").hasAnyRole("ADMIN", "MEMBER")
			.antMatchers("/", "/js/**", "/css/**", "/images/**", "/**").permitAll()
			.antMatchers("/h2-console/**").permitAll()
			.anyRequest().authenticated()
			.and().formLogin()
				.loginPage("/login").permitAll()
			.and().logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout").permitAll()
			.and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}

}
