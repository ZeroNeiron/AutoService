package autoservice.service.impl;

import autoservice.model.Favor;
import autoservice.repository.FavorRepository;
import autoservice.service.FavorService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FavorServiceImpl implements FavorService {
    private final FavorRepository favorRepository;

    public FavorServiceImpl(FavorRepository favorRepository) {
        this.favorRepository = favorRepository;
    }

    @Override
    public Favor getById(Long id) {
        return favorRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get favor by id " + id));
    }

    @Override
    public Favor create(Favor favor) {
        return favorRepository.save(favor);
    }

    @Override
    public List<Favor> getAll() {
        return favorRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        favorRepository.deleteById(id);
    }

    @Override
    public List<Favor> getFavorsByRepairmanIdAndOrderId(Long repairmanId, Long orderId) {
        return favorRepository.getFavorsByRepairmanIdAndOrderId(repairmanId, orderId);
    }
}
