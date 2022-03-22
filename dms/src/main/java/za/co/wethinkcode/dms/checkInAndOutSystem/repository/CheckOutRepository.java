package za.co.wethinkcode.dms.checkInAndOutSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckInEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckOutEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CheckOutRepository extends JpaRepository<CheckOutEntity, String> {

    Optional<CheckOutEntity> findCheckOutEntityByDateAndUsername(LocalDate date, String username);

    Optional<CheckInEntity> findCheckInEntityByDateAndUsername(LocalDate date, String username);

    List<CheckOutEntity> findCheckOutEntitiesByUsername(String username);

    boolean existsCheckInEntityByDateAndUsername(LocalDate date, String username);
}
