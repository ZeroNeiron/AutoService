package autoservice.repository;

import autoservice.model.Order;
import autoservice.model.Repairman;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepairmanRepository extends JpaRepository<Repairman, Long> {
    @Query("select o from Order o "
            + "inner join fetch o.favors f "
            + "inner join fetch f.repairman r "
            + "where r.id = :id")
    List<Order> getAllCompletedOrders(Long id);

    @Query("select rep from Repairman rep where rep.id in "
            + "(select r.id from Order o "
            + "inner join o.favors f "
            + "inner join f.repairman r "
            + "where o.id = :id)")
    List<Repairman> getAllRepairmansByOrderId(Long id);
}
