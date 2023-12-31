package ShoppingProejct.shoppingmall.service;

import ShoppingProejct.shoppingmall.Domain.Member.Member;
import ShoppingProejct.shoppingmall.Domain.Member.MemberCreateDto;
import ShoppingProejct.shoppingmall.Domain.Member.MemberInfoDto;
import ShoppingProejct.shoppingmall.Domain.Member.MemberLoginDto;
import ShoppingProejct.shoppingmall.security.jwt.JwtProvider;
import ShoppingProejct.shoppingmall.exception.LoginException;
import ShoppingProejct.shoppingmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder; //config 폴더 아래 관리
    private final JwtProvider jwtService;

    // 회원 생성
    @Transactional
    public void saveMember(MemberCreateDto memberCreateDto) {

        // 비밀번호 암호화 후 저장
        String encodedPwd = passwordEncoder.encode(memberCreateDto.getPassword());
        memberCreateDto.setPassword(encodedPwd);
        memberRepository.save(memberCreateDto.toEntity());
    }

    public Object[] loginMember(MemberLoginDto memberLoginDto){
        Member findMember = memberRepository.findByEmail(memberLoginDto.getEmail());

        // 멤버 조회 안되면 아이디 없음 에러
        if(findMember == null){
            throw new LoginException("email not found");
        }

        // 비밀번호 틀림
        if(!passwordEncoder.matches(memberLoginDto.getPassword(), findMember.getPassword())){
            throw new LoginException("wrong password");
        }

        // 토큰 생성
        String accessToken = jwtService.createAccessToken(String.valueOf(findMember.getId()));

        // 회원정보, 토큰 리턴
        return new Object[]{MemberInfoDto.entityToDto(findMember), accessToken};

    }


}
