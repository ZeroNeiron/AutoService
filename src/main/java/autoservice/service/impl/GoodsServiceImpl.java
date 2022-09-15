package autoservice.service.impl;

import autoservice.model.Goods;
import autoservice.repository.GoodsRepository;
import autoservice.service.GoodsService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository goodsRepository;

    public GoodsServiceImpl(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Override
    public Goods getById(Long id) {
        return goodsRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get goods by id " + id));
    }

    @Override
    public Goods create(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public List<Goods> getAll() {
        return goodsRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        goodsRepository.deleteById(id);
    }
}
