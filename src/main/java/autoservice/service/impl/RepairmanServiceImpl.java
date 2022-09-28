package autoservice.service.impl;

import autoservice.model.Order;
import autoservice.model.Repairman;
import autoservice.repository.RepairmanRepository;
import autoservice.service.RepairmanService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RepairmanServiceImpl implements RepairmanService {
    private final RepairmanRepository repairmanRepository;

    public RepairmanServiceImpl(RepairmanRepository repairmanRepository) {
        this.repairmanRepository = repairmanRepository;
    }

    @Override
    public Repairman getById(Long id) {
        return repairmanRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get car by id " + id));
    }

    @Override
    public Repairman create(Repairman repairman) {
        return repairmanRepository.save(repairman);
    }

    @Override
    public List<Repairman> getAll() {
        return repairmanRepository.findAll();
    }

    @Override
    public Repairman addCompletedOrder(Long id, Order order) {
        Repairman repairman = getById(id);
        repairman.getCompletedOrders().add(order);
        return repairman;
    }

    @Override
    public List<Order> getAllCompletedOrdersById(Long id) {
        return repairmanRepository.getAllCompletedOrders(id);
    }

    @Override
    public List<Repairman> getAllRepairmensByOrderId(Long id) {
        return repairmanRepository.getAllRepairmansByOrderId(id);
    }
}
