package ShoppingProejct.shoppingmall.repository;

import ShoppingProejct.shoppingmall.Domain.Member.Member;
import ShoppingProejct.shoppingmall.Domain.Member.MemberCreateDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    // 이메일로 회원정보 찾기
    public Member findByEmail(String email) {
        // 찾지 못하면 null 리턴
        try {
            return em.createQuery("select m from Member m where m.email = :email", Member.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Member findById(int memberId) {
        try{
            return em.createQuery("select m from Member m where m.id = :memberId", Member.class)
                    .setParameter("memberId", memberId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }


    }



}
