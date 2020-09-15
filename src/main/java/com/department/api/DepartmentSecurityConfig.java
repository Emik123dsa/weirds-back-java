package com.department.api;

import com.department.api.filter.JwtRequestFilter;
import com.department.api.helpers.JwtAuthenticationEntryPoint;
import com.department.api.service.DepartmentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class DepartmentSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DepartmentUserService departmentUserService;

    private final JwtRequestFilter jwtRequestFilter;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    protected static  final String ENTRY_LOGIN = "/api/signin";

    protected static  final String ENTRY_SIGN_UP = "/api/signup";

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(departmentUserService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Autowired
    public DepartmentSecurityConfig(DepartmentUserService departmentUserService, JwtRequestFilter jwtRequestFilter, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.departmentUserService = departmentUserService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().
                    antMatchers(ENTRY_LOGIN).permitAll().
                    antMatchers(ENTRY_SIGN_UP).permitAll().
                    anyRequest().authenticated().
                    and().
                logout().permitAll().
                    and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().
                sessionManagement().
                    sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
