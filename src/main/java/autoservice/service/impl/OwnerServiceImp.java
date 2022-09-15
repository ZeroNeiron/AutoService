package autoservice.service.impl;

import autoservice.model.Car;
import autoservice.model.Order;
import autoservice.model.Owner;
import autoservice.repository.OwnerRepository;
import autoservice.service.OrderService;
import autoservice.service.OwnerService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImp implements OwnerService {
    private final OwnerRepository ownerRepository;

    private final OrderService orderService;

    public OwnerServiceImp(OwnerRepository ownerRepository,
                           OrderService orderService) {
        this.ownerRepository = ownerRepository;
        this.orderService = orderService;
    }

    @Override
    public Owner getById(Long id) {
        return ownerRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get owner by id " + id));
    }

    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner addCar(Long id, Car car) {
        Owner owner = getById(id);
        owner.getCars().add(car);
        return create(owner);
    }

    @Override
    public Owner addOrder(Long id, Order order) {
        Owner owner = getById(id);
        owner.getOrders().add(order);
        return create(owner);
    }

    @Override
    public Owner getOwnerByCarId(Long id) {
        return ownerRepository.getOwnerByCarId(id);
    }
}
