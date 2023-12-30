package ShoppingProejct.shoppingmall.Domain.Member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String password;

    private String image;

    @CreatedDate
    private LocalDateTime createdDate;


    protected Member() {}

    /**
     * 회원가입시 사용 Builder
     */
    @Builder
    public Member(String email, String name, String password, String image){
        this.email = email;
        this.name = name;
        this.password = password;
        this.image = image;
    }
}
