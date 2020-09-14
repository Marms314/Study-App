package com.Learnification.StudyApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/flashcard/create", "/flashcard/manage").hasRole("ADMIN")
                .antMatchers("/quiz/create", "/quiz/manage").hasRole("ADMIN")
                .antMatchers("/category/create", "/category/manage").hasRole("ADMIN")
                .antMatchers("/user/create", "/user/manage").hasRole("ADMIN")
                .antMatchers("/").hasAnyRole("USER", "ADMIN")
                .and().formLogin().loginPage("/login")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/perform-logout"))
                .logoutSuccessUrl("/login?loggedOut=true")
                .invalidateHttpSession(true)        // set invalidation state when logout
                .deleteCookies("JSESSIONID");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
