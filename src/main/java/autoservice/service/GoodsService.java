package autoservice.service;

import autoservice.model.Goods;
import java.util.List;

public interface GoodsService {
    Goods getById(Long id);

    Goods create(Goods goods);

    List<Goods> getAll();

    void deleteById(Long id);
}
