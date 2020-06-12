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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    UserService userService;
    @Autowired
    OrderMapper orderMapper;

    @PostMapping("/complete")
    public void completeOrder(@RequestBody OrderRequestDto orderDto) {
        ShoppingCart shoppingCart = shoppingCartService.getById(orderDto.getShoppingCartId());
        orderService.completeOrder(shoppingCart.getTickets(), shoppingCart.getUser());
    }

    @GetMapping("/getbyuser")
    public List<OrderResponseDto> getOrderById(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.getById(userId))
                .stream()
                .map(order -> orderMapper.getOrderResponseDtoFromOrder(order))
                .collect(Collectors.toList());
    }
}
