package autoservice.service;

import autoservice.model.Favor;
import autoservice.model.Goods;
import autoservice.model.Order;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order getById(Long id);

    Order create(Order order);

    List<Order> getAll();

    void deleteById(Long id);

    Order addGoods(Long id, Goods goods);

    Order addFavor(Long id, Favor favor);

    Order addFavorById(Long orderId, Long favorId);

    BigDecimal setFinalPrice(Long id, int bonus);
}
