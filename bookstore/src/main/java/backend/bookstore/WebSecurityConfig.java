package backend.bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        System.out.println("Password encoder bean initialized");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers("/css/**").permitAll() // allow static resources
                        .requestMatchers("/index", "/books", "/api/books/**").permitAll() // allow public endpoints
                        .requestMatchers("/h2-console/**").permitAll() // for h2console
                        .anyRequest().authenticated()) // require authentication for all other requests
                // Käyttää HTTP Basic -autentikointia, oletusasetuksilla (REST API -endpointtien
                // testaus Postmanilla).
                .httpBasic(Customizer.withDefaults())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions
                        .disable())) // for h2console
                .formLogin(formlogin -> formlogin.loginPage("/login")
                        .defaultSuccessUrl("/books", true)
                        .permitAll()) // allow form login
                .logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf.disable()); // not for production, but needed to allow POST requests from Postman

        return http.build();

    }

}
