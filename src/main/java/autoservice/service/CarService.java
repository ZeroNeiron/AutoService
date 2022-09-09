package autoservice.service;

import autoservice.model.Car;
import java.util.List;

public interface CarService {
    Car getById(Long id);

    Car create(Car car);

    List<Car> getAll();

    void deleteById(Long id);
}
