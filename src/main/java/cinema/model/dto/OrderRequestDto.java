package cinema.model.dto;

import lombok.Data;

@Data
public class OrderRequestDto {
    Long shoppingCartId;

    public Long getShoppingCartId() {
        return shoppingCartId;
    }
}
