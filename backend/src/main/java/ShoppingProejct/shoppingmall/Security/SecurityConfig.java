package ShoppingProejct.shoppingmall.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(HttpBasicConfigurer::disable);
        http.csrf(CsrfConfigurer::disable); // 외부 POST 요청을 받아야하니 csrf는 꺼준다.
        http.cors(Customizer.withDefaults()); // CORS를 커스텀하려면 이렇게
        http.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/**").permitAll().anyRequest().authenticated());

        return http.build();
    }
}
