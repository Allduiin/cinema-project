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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService,
                           UserService userService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestBody @Valid OrderRequestDto orderDto) {
        ShoppingCart shoppingCart = shoppingCartService.getByUser(
                userService.getById(orderDto.getUserId()));
        orderService.completeOrder(shoppingCart.getTickets(), shoppingCart.getUser());
    }

    @GetMapping("/by-user")
    public List<OrderResponseDto> getOrderById(Authentication authentication) {
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        return orderService.getOrderHistory(userService.getByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found.")))
                .stream()
                .map(orderMapper::getOrderResponseDtoFromOrder)
                .collect(Collectors.toList());
    }
}
