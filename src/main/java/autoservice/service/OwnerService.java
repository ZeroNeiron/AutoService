package autoservice.service;

import autoservice.model.Car;
import autoservice.model.Order;
import autoservice.model.Owner;
import java.util.List;

public interface OwnerService {
    Owner getById(Long id);

    Owner create(Owner owner);

    List<Owner> getAll();

    void deleteById(Long id);

    Owner addCar(Long id, Car car);

    Owner addOrder(Long id, Order order);

    Owner getOwnerByCarId(Long id);
}
