package heavenmentiel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import heavenmentiel.services.AuthenticationService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
	}
	
	@Autowired
	RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Autowired
	RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
	
	@Autowired
	RestAuthenticationFailureHandler restAuthenticationFailureHandler;
	
	@Autowired
	RestAccessDeniedHandler restAccessDeniedHandler;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.sessionManagement().and()
		.authorizeRequests().and()
		.exceptionHandling()
		.authenticationEntryPoint(restAuthenticationEntryPoint)
		.accessDeniedHandler(restAccessDeniedHandler)
		.and()
		.formLogin().loginProcessingUrl("/authenticate")
		.successHandler(restAuthenticationSuccessHandler).permitAll()
		.failureHandler(restAuthenticationFailureHandler)
		.usernameParameter("username")
		.passwordParameter("password").permitAll().and()
		.logout().logoutUrl("/logout").logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
		.permitAll().and()
		.httpBasic().and()
		.csrf()
		.disable();
	}
}
