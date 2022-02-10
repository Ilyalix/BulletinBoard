package com.config.security;

import com.config.ConfigApp;
import com.jwt_processing.JwtAuthenticationEntryPoint;
import com.jwt_processing.JwtRequestFilter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@Import({EncoderConfig.class, ConfigApp.class})
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    PasswordEncoder encoder;

    UserDetailsService service;

    JwtAuthenticationEntryPoint entryPoint;

    JwtRequestFilter filter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .mvcMatchers("/jwt/registration", "/jwt/authenticate")
                .permitAll()
                .mvcMatchers(HttpMethod.GET, "/advertisement/*", "/matchingAd/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .mvcMatchers(HttpMethod.POST, "/advertisement/*", "/matchingAd/*").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/advertisement/*", "/matchingAd/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .mvcMatchers(HttpMethod.DELETE, "/advertisement/*", "/matchingAd/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .mvcMatchers("/role/*").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Add a filter to validate the tokens with every request
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean(name = "mvcHandlerMappingIntrospector")
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(service)
                .passwordEncoder(encoder);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
