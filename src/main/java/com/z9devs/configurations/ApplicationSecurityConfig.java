package com.z9devs.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Spring controlla questo file per la sicurezza
@Configuration
@EnableWebSecurity
// https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/configuration/WebSecurityConfigurerAdapter.html
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	/*
	 * @Bean
	 * 
	 * @Override protected UserDetailsService userDetailsService() {
	 * List<UserDetails> users = new ArrayList<>();
	 * users.add(User.withDefaultPasswordEncoder().username("prova").password(
	 * "prova").roles("USER").build()); return new
	 * InMemoryUserDetailsManager(users); }
	 */

	@Autowired
	private UserDetailsService userDetailsService;

	// Si crea un metodo "authProvider", in cui si recuperano i dati dal db
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		// Si setta il service da utilizzare per recuperare i dati
		provider.setUserDetailsService(userDetailsService);
		// Questo non va bene perché non facciamo encoding della pw
		// provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		// bcrypt è molto sicuro
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	// Per gestire la sessione

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().fullyAuthenticated()

				.and().formLogin().loginPage("/login").loginProcessingUrl("/doLogin")
				.failureUrl("/login?error=authenticationFailed")

				.and().logout(logout -> logout.logoutSuccessUrl("/login").invalidateHttpSession(true)
						.deleteCookies("JSESSIONID").logoutRequestMatcher(new AntPathRequestMatcher("/logout")));

		// https://www.baeldung.com/spring-security-session
		http.sessionManagement().maximumSessions(1).expiredUrl("/login?error=expiredSession").and()
				.invalidSessionUrl("/login?error=invalidSession")
		// .maxSessionsPreventsLogin(true) -> prevenire il login
		;
	}

	@Override
	public void configure(WebSecurity webSecurity) {
		webSecurity.ignoring().antMatchers("/sec/**").and().ignoring().antMatchers("/login").and().ignoring()
				.antMatchers("/login/**");
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
