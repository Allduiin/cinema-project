package cinema.controllers;

import cinema.mappers.OrderMapper;
import cinema.model.ShoppingCart;
import cinema.model.dto.OrderRequestDto;
import cinema.model.dto.OrderResponseDto;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderMapper orderMapper;

    @PostMapping("/complete")
    public void completeOrder(@RequestBody @Valid OrderRequestDto orderDto) {
        ShoppingCart shoppingCart = shoppingCartService.getByUser(
                userService.getById(orderDto.getUserId()));
        orderService.completeOrder(shoppingCart.getTickets(), shoppingCart.getUser());
    }

    @GetMapping("/by-user")
    public List<OrderResponseDto> getOrderById(Authentication authentication) {
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        return orderService.getOrderHistory(userService.findByEmail(email))
                .stream()
                .map(order -> orderMapper.getOrderResponseDtoFromOrder(order))
                .collect(Collectors.toList());
    }
}
