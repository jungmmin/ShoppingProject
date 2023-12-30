package ShoppingProejct.shoppingmall.Domain.Member;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 회원 가입시 사용하는 멤버 DTO
 */
@Data
@AllArgsConstructor

public class MemberCreateDto {

    private String email;
    private String password;
    private String name;
    private String image;

    public Member toEntity(){
        return  Member.builder()
                .email(this.email)
                .name(this.name)
                .password(this.password)
                .image(this.image)
                .build();

    }
}
