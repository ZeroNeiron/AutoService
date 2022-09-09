package autoservice.service;

import autoservice.model.Favor;
import java.util.List;

public interface FavorService {
    Favor getById(Long id);

    Favor create(Favor favor);

    List<Favor> getAll();

    void deleteById(Long id);

    List<Favor> getFavorsByRepairmanIdAndOrderId(Long repairmanId, Long orderId);
}
