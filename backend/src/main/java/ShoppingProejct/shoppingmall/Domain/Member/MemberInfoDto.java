package ShoppingProejct.shoppingmall.Domain.Member;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberInfoDto {
    private String email;
    private String name;
    private String image;


    public static MemberInfoDto entityToDto(Member member){
        return MemberInfoDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .image(member.getImage())
                .build();
    }
}
