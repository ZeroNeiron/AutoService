package autoservice.repository;

import autoservice.model.Favor;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FavorRepository extends JpaRepository<Favor, Long> {
    List<Favor> getFavorsByRepairmanIdAndOrderId(Long repairmanId, Long orderId);

    @Transactional
    @Modifying
    @Query("update Favor f set f.status = :status where f.id = :id")
    void changeFavorStatusById(Long id, Favor.Status status);
}
