package autoservice.repository;

import autoservice.model.Repairman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairmanRepository extends JpaRepository<Repairman, Long> {
}
