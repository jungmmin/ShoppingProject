package ShoppingProejct.shoppingmall.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResult<T> {
    // 리턴 개수
    private int count;

    private T data;
}
