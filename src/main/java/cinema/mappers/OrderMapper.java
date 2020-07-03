package cinema.mappers;

import cinema.model.Order;
import cinema.model.dto.OrderResponseDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;

    public OrderMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public OrderResponseDto getOrderResponseDtoFromOrder(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setTickets(ticketMapper.getDtoTicketsFromTickets(order.getTickets()));
        orderResponseDto.setTime(order.getTime());
        orderResponseDto.setUserId(order.getUser().getId());
        return orderResponseDto;
    }
}
