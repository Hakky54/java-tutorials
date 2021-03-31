package nl.altindag.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .x509(Customizer.withDefaults())
                .authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService mapReactiveUserDetailsService() {
        UserDetails bob = User.withUsername("black-hole")
                .authorities(new SimpleGrantedAuthority("ROLE_USER"))
                .password("")
                .build();

        return new MapReactiveUserDetailsService(bob);
    }

}
