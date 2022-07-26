package pl.coderslab.charity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import pl.coderslab.charity.users.SpringDataUserDetailsService;
import pl.coderslab.charity.users.SuccessLoginHandler;

import java.util.HashMap;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SuccessLoginHandler();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

    @Bean
    public ExceptionMappingAuthenticationFailureHandler loginFailureHandler() {

        ExceptionMappingAuthenticationFailureHandler exceptionMapping = new ExceptionMappingAuthenticationFailureHandler();
        HashMap<String, String> failureUrlMap = new HashMap<>();
        failureUrlMap.put(BadCredentialsException.class.getCanonicalName(), "/login/error");
        failureUrlMap.put(CredentialsExpiredException.class.getCanonicalName(), "/login/error");
        failureUrlMap.put(AuthenticationServiceException.class.getCanonicalName(), "/login/error");
        failureUrlMap.put(LockedException.class.getCanonicalName(), "/login/locked");
        failureUrlMap.put(AccountExpiredException.class.getCanonicalName(), "/reset/password");
        failureUrlMap.put(DisabledException.class.getCanonicalName(), "/login/disabled");

        exceptionMapping.setExceptionMappings(failureUrlMap);
        return exceptionMapping;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/resources/css/**", "/resources/images/**", "/resources/js/**").permitAll()
                .antMatchers("/donations/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().formLogin().loginPage("/login").usernameParameter("email")
                .defaultSuccessUrl("/")
                .successHandler(successHandler())
                .failureHandler(loginFailureHandler())
                .and().logout().logoutSuccessUrl("/")
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/login");
    }
}
