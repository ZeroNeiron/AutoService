package autoservice.service.impl;

import autoservice.model.Favor;
import autoservice.model.Goods;
import autoservice.model.Order;
import autoservice.repository.OrderRepository;
import autoservice.service.FavorService;
import autoservice.service.GoodsService;
import autoservice.service.OrderService;
import autoservice.service.PriceService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final FavorService favorService;
    private final GoodsService goodsService;
    private final PriceService priceService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            FavorService favorService,
                            GoodsService goodsService, PriceService priceService) {
        this.orderRepository = orderRepository;
        this.favorService = favorService;
        this.goodsService = goodsService;
        this.priceService = priceService;
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get car by id " + id));
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order addGoods(Long id, Goods goods) {
        Order order = getById(id);
        order.getGoods().add(goodsService.create(goods));
        return create(order);
    }

    @Override
    public Order addFavor(Long id, Favor favor) {
        Order order = getById(id);
        order.getFavors().add(favorService.create(favor));
        return create(order);
    }

    @Override
    public Order addFavorById(Long orderId, Long favorId) {
        Order order = getById(orderId);
        order.getFavors().add(favorService.getById(favorId));
        return create(order);
    }

    @Override
    public BigDecimal setFinalPrice(Long id, int bonus) {
        Order order = getById(id);
        BigDecimal sum = priceService.getTotalPrice(order, bonus);
        order.setFinalPrice(sum);
        create(order);
        return sum;
    }
}
