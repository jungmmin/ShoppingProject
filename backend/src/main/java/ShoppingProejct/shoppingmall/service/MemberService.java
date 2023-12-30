package ShoppingProejct.shoppingmall.service;

import ShoppingProejct.shoppingmall.Domain.Member.MemberCreateDto;
import ShoppingProejct.shoppingmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 생성
    @Transactional
    public void saveMember(MemberCreateDto memberCreateDto) {

        // 비밀번호 암호화 후 저장
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPwd = encoder.encode(memberCreateDto.getPassword());
        memberCreateDto.setPassword(encodedPwd);
        memberRepository.save(memberCreateDto.toEntity());
    }


}
