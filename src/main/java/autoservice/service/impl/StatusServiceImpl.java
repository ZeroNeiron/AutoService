package autoservice.service.impl;

import autoservice.model.Favor;
import autoservice.model.Order;
import autoservice.model.Repairman;
import autoservice.service.FavorService;
import autoservice.service.OrderService;
import autoservice.service.RepairmanService;
import autoservice.service.StatusService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    private final FavorService favorService;
    private final OrderService orderService;
    private final RepairmanService repairmanService;

    public StatusServiceImpl(FavorService favorService,
                             OrderService orderService,
                             RepairmanService repairmanService) {
        this.favorService = favorService;
        this.orderService = orderService;
        this.repairmanService = repairmanService;
    }

    @Override
    public Favor changeFavorStatus(Long id, Favor.Status status) {
        return favorService.create(favorService.getById(id).setStatus(status));
    }

    @Override
    public Order changeOrderStatus(Long id, Order.Status status) {
        Order order = orderService.getById(id);
        order.setStatus(status);
        List<Repairman> repairmanList;
        switch (status) {
            case COMPLETED: {
                repairmanList = repairmanService.getAllRepairmensByOrderId(id);
                saveData(order, repairmanList);
                break;
            }
            case FAILURE: {
                repairmanList = changeToFailure(order);
                saveData(order, repairmanList);
                break;
            }
            default:
        }
        return order;
    }

    private List<Repairman> changeToFailure(Order order) {
        Favor priceFavor = order.getFavors().stream()
                .findFirst()
                .get();
        priceFavor.setPrice(BigDecimal.valueOf(500));
        return List.of(priceFavor.getRepairman());
    }

    private void saveData(Order order, List<Repairman> repairmanList) {
        order.setEndDate(LocalDateTime.now());
        orderService.create(order);
        repairmanList.stream().distinct().forEach(r ->
                repairmanService.create(repairmanService.addCompletedOrder(r.getId(), order)));
    }
}
