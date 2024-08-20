/**
 * This file represents the SecurityConfig class, which is responsible for configuring the security settings of the application.
 * It is located in the package com.dio.config.
 * 
 * The SecurityConfig class is used to define the security configurations, such as authentication and authorization, for the application.
 * It is an important class for ensuring the security of the application and protecting sensitive data.
 * 
 * This class is typically used in conjunction with other classes and components to provide a secure environment for the application.
 * It may contain methods and configurations related to authentication providers, access control, session management, and more.
 * 
 * To use the SecurityConfig class, it needs to be properly configured and registered in the application's configuration.
 * This can be done by adding the necessary annotations and configurations to the class.
 * 
 * Example usage:
 * 
 *     @Configuration
 *     @EnableWebSecurity
 *     public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
 *     
 *         @Autowired
 *         private SecurityConfig securityConfig;
 *     
 *         @Override
 *         protected void configure(HttpSecurity http) throws Exception {
 *             http
 *                 .authorizeRequests()
 *                     .antMatchers("/public/**").permitAll()
 *                     .anyRequest().authenticated()
 *                 .and()
 *                     .formLogin()
 *                         .loginPage("/login")
 *                         .permitAll()
 *                 .and()
 *                     .logout()
 *                         .permitAll();
 *         }
 *     
 *         @Override
 *         protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 *             auth.authenticationProvider(securityConfig.authenticationProvider());
 *         }
 *     
 *     }
 */
package com.dio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.permitAll())
            .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}