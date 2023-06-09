package com.evadeeva.evadeeva.config;

import com.evadeeva.evadeeva.securities.CustomUserDetailsService;
import com.evadeeva.evadeeva.securities.JwtAuthenticationEntryPoint;
import com.evadeeva.evadeeva.securities.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(proxyTargetClass = true)
public class SecurityConfig {

    private CustomUserDetailsService userDetailsService;
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private CorsConfig corsConfig;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter();
    }
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .cors().and()
        		.csrf().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                    .requestMatchers("/api/register").permitAll()
                    .requestMatchers("/api/login").permitAll()
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
                    .requestMatchers("/api/category/**").permitAll()
                    .requestMatchers("/api/product/**").permitAll()
                    .requestMatchers("/api/banner/**").permitAll()
                    .requestMatchers("/api/article/**").permitAll()
                    .requestMatchers("/api/company/**").permitAll()
                    .requestMatchers("/api/user/**").permitAll()
                    .requestMatchers("/api/cart/**").permitAll()
                    .requestMatchers("/error").permitAll()
                    .anyRequest().authenticated();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    protected void filterChain(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((userDetailsService)).passwordEncoder((passwordEncoder()));
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}