package autoservice.repository;

import autoservice.model.Favor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavorRepository extends JpaRepository<Favor, Long> {
    List<Favor> getFavorsByRepairman_IdAndOrder_Id(Long repairmanId, Long orderId);
}