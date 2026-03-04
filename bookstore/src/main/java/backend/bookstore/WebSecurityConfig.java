package backend.bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
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
                                                .requestMatchers("/login", "/css/**").permitAll() // allow static
                                                                                                  // resources
                                                .requestMatchers("/index", "/api/books/**").permitAll() // allow
                                                                                                        // public
                                                                                                        // endpoints
                                                .requestMatchers("/h2-console/**").permitAll() // for h2console
                                                .anyRequest().authenticated()) // require authentication for all other
                                                                               // requests
                                // Käyttää HTTP Basic -autentikointia, oletusasetuksilla (REST API -endpointtien
                                // testaus Postmanilla).
                                .httpBasic(Customizer.withDefaults())
                                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions
                                                .disable())) // for h2console
                                .formLogin(formlogin -> formlogin
                                                .defaultSuccessUrl("/books", true)
                                                .permitAll()) // allow form login
                                .logout(logout -> logout.permitAll())
                                .csrf(csrf -> csrf.disable()); // not for production, but needed to allow POST requests
                                                               // from Postman

                return http.build();

        }

        @Bean
        public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
                var encoder = passwordEncoder();
                var user = org.springframework.security.core.userdetails.User.withUsername("user")
                                .password(encoder.encode("user")).roles("USER").build();
                var admin = org.springframework.security.core.userdetails.User.withUsername("admin")
                                .password(encoder.encode("admin")).roles("ADMIN").build();
                return new org.springframework.security.provisioning.InMemoryUserDetailsManager(user, admin);
        }

}
