package autoservice.controller;

import autoservice.dto.request.FavorRequestDto;
import autoservice.dto.request.GoodsRequestDto;
import autoservice.dto.request.OrderRequestDto;
import autoservice.dto.response.OrderResponseDto;
import autoservice.model.Order;
import autoservice.model.Owner;
import autoservice.service.OrderService;
import autoservice.service.OwnerService;
import autoservice.service.StatusService;
import autoservice.service.mapper.FavorMapper;
import autoservice.service.mapper.GoodsMapper;
import autoservice.service.mapper.OrderMapper;
import java.math.BigDecimal;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final GoodsMapper goodsMapper;
    private final OwnerService ownerService;
    private final FavorMapper favorMapper;
    private final StatusService statusService;

    public OrderController(OrderMapper orderMapper,
                           OrderService orderService,
                           GoodsMapper goodsMapper,
                           OwnerService ownerService,
                           FavorMapper favorMapper,
                           StatusService statusService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.goodsMapper = goodsMapper;
        this.ownerService = ownerService;
        this.favorMapper = favorMapper;
        this.statusService = statusService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderMapper.mapToModel(orderRequestDto);
        orderService.create(order);
        Owner owner = ownerService.getOwnerByCarId(orderRequestDto.getCarId());
        ownerService.create(ownerService.addOrder(owner.getId(), order));
        return orderMapper.mapToDto(order);
    }

    @PostMapping("/{id}/goods")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto addGoods(@PathVariable Long id,
                                     @Valid @RequestBody GoodsRequestDto goodsRequestDto) {
        return orderMapper
                .mapToDto(orderService
                        .create(orderService
                                .addGoods(id, goodsMapper.mapToModel(goodsRequestDto))));
    }

    @PostMapping("/{id}/favors")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto addFavor(@PathVariable Long id,
                                     @Valid @RequestBody FavorRequestDto favorRequestDto) {
        return orderMapper
                .mapToDto(orderService
                        .create(orderService
                                .addFavor(id, favorMapper.mapToModel(favorRequestDto))));
    }

    @PutMapping("/{id}")
    public OrderResponseDto updateOrder(@PathVariable Long id,
                                      @Valid @RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderMapper.mapToModel(orderRequestDto);
        order.setId(id);
        return orderMapper.mapToDto(orderService.create(order));
    }

    @PutMapping("/{id}/status")
    public OrderResponseDto updateOrderStatus(@PathVariable Long id,
                                              @RequestParam String newStatus) {
        return orderMapper
                .mapToDto(statusService
                        .changeOrderStatus(id, Order.Status.valueOf(newStatus)));
    }

    @GetMapping("/{id}/price")
    public BigDecimal getOrderPrice(@PathVariable Long id, @RequestParam Integer bonus) {
        return orderService.setFinalPrice(id, bonus);
    }
}
