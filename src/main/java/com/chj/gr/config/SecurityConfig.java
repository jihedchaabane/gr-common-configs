//package com.chj.gr.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Penser à "gr-conf-swagger-aggregator".
 */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .cors().and() // Activer la prise en charge de CORS
//            .csrf().disable() // Désactiver CSRF pour simplifier (optionnel, selon vos besoins)
//            .authorizeRequests()
//                .antMatchers("/actuator/**").permitAll() // Autoriser l'accès non authentifié aux endpoints Actuator
//                .antMatchers("/v3/api-docs/**").permitAll() // Autoriser l'accès à /v3/api-docs
//                .anyRequest().authenticated() // Autres endpoints nécessitent une authentification
//            .and()
//            .httpBasic(); // Activer l'authentification HTTP Basic (optionnel)
//    }
//}


//package com.chj.gr.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//            .antMatchers("/swagger-ui/**", "/v2/api-docs/**", "/swagger-resources/**").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .csrf().disable();
//    }
//}
