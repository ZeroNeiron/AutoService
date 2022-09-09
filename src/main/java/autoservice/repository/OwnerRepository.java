package autoservice.repository;

import autoservice.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query("select o from Owner o inner join o.cars c where c.id = :id")
    Owner getOwnerByCarId(Long id);
}
