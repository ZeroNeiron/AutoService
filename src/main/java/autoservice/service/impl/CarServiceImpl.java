package autoservice.service.impl;

import autoservice.model.Car;
import autoservice.repository.CarRepository;
import autoservice.service.CarService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car getById(Long id) {
        return carRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get car by id " + id));
    }

    @Override
    public Car create(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }
}
