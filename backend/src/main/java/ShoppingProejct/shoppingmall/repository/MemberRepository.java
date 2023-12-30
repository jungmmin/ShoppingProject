package ShoppingProejct.shoppingmall.repository;

import ShoppingProejct.shoppingmall.Domain.Member.Member;
import ShoppingProejct.shoppingmall.Domain.Member.MemberCreateDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    // 회원 생성, 수정
    public void save(Member member){
        if (member.getId() == null) {
            em.persist(member);
        } else{
            em.merge(member);
        }
    }



}
