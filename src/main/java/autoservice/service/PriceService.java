package autoservice.service;

import autoservice.model.Order;
import java.math.BigDecimal;

public interface PriceService {
    BigDecimal getTotalPrice(Order order, int bonus);
}
