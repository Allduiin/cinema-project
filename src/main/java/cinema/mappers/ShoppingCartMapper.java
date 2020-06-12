package cinema.mappers;

import cinema.model.ShoppingCart;
import cinema.model.dto.ShoppingCartResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    @Autowired
    TicketMapper ticketMapper;

    public ShoppingCartResponseDto getShoppingCartResponseDtoFrom(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        shoppingCartResponseDto.setTickets(
                ticketMapper.getDtoTicketsFromTickets(shoppingCart.getTickets()));
        shoppingCartResponseDto.setUserId(shoppingCart.getUser().getId());
        return shoppingCartResponseDto;
    }
}
