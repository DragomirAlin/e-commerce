package ro.dragomiralin.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.ecommerce.controller.dto.ListResponse;
import ro.dragomiralin.ecommerce.controller.dto.OrderDTO;
import ro.dragomiralin.ecommerce.controller.dto.PageDTO;
import ro.dragomiralin.ecommerce.controller.dto.UserDTO;
import ro.dragomiralin.ecommerce.controller.mapper.OrderDTOMapper;
import ro.dragomiralin.ecommerce.controller.request.CustomResponse;
import ro.dragomiralin.ecommerce.domain.order.OrderService;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderDTOMapper mapper;

    @PostMapping
    public ResponseEntity<CustomResponse<OrderDTO>> create(@AuthenticationPrincipal UserDTO userDTO, @RequestBody OrderDTO orderDTO) {
        var order = orderService.create(userDTO.getId(), mapper.toOrderDO(orderDTO));
        return ResponseEntity.ok(CustomResponse.single(mapper.toOrderDTO(order)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<OrderDTO>> get(@AuthenticationPrincipal UserDTO userDTO, @PathVariable long id) {
        var order = orderService.get(userDTO.getId(), id);
        return ResponseEntity.ok(CustomResponse.single(mapper.toOrderDTO(order)));
    }

    @GetMapping
    public ResponseEntity<CustomResponse<List<OrderDTO>>> list(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        var orders = orderService.list(userDTO.getId(), page, size);

        var ordersPage = mapper.toPageDTO(orders);
        var customResponse = ListResponse.build(ordersPage);
        return ResponseEntity.ok(CustomResponse.list(customResponse));
    }

}
