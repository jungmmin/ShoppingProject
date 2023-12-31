package ShoppingProejct.shoppingmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CommonConfig {

    // 회원 비밀번호 암호화 설정
    @Bean
    public PasswordEncoder encodePassword(){
        return new BCryptPasswordEncoder();
    }
}
