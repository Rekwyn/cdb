package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
        auth.inMemoryAuthentication()
          .withUser("user").password("{noop}userPass").roles("USER")
          .and()
          .withUser("admin").password("{noop}adminPass").roles("ADMIN");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	      .csrf().disable()
	      .authorizeRequests()
	      .antMatchers("/login*").permitAll()
	      .anyRequest().authenticated()
	      .and()
	      .formLogin()
	      .defaultSuccessUrl("/", true)
	      .failureUrl("/auth/login?error=true")
	      .and()
	      .logout().logoutSuccessUrl("/auth/login");
	}
	
}
