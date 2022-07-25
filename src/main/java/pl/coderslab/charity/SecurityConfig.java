package pl.coderslab.charity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
    public AuthenticationSuccessHandler successHandler(){
        return new SuccessLoginHandler();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

    @Bean
    public ExceptionMappingAuthenticationFailureHandler loginMappingFailureHandler() {
        ExceptionMappingAuthenticationFailureHandler exceptionMapping = new ExceptionMappingAuthenticationFailureHandler();
        HashMap<String, String> failureUrlMap = new HashMap<String, String>();
        failureUrlMap.put("org.springframework.security.authentication.BadCredentialsException", "/login/error");
        failureUrlMap.put("org.springframework.security.authentication.CredentialsExpiredException", "/login/error");
        failureUrlMap.put("org.springframework.security.authentication.AuthenticationServiceException", "/login/error");
        exceptionMapping.setExceptionMappings(failureUrlMap);
        return exceptionMapping;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password("{noop}user123").roles("USER")
                .and()
                .withUser("admin1").password("{noop}admin123").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/donations/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().formLogin().loginPage("/login").usernameParameter("email")
                .defaultSuccessUrl("/")
                .successHandler(successHandler())
                .failureHandler(loginMappingFailureHandler())
                .and().logout().logoutSuccessUrl("/")
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/login");
    }
}
