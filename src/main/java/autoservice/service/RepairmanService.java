package autoservice.service;

import autoservice.model.Order;
import autoservice.model.Repairman;
import java.util.List;

public interface RepairmanService {

    Repairman getById(Long id);

    Repairman create(Repairman repairman);

    List<Repairman> getAll();

    Repairman addCompletedOrder(Long id, Order order);

    List<Order> getAllCompletedOrdersById(Long id);

    List<Repairman> getAllRepairmensByOrderId(Long id);
}
