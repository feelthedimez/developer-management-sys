package za.co.wethinkcode.dms.checkInAndOutSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckInEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CheckInRepository extends JpaRepository<CheckInEntity, String> {

    Optional<CheckInEntity> findCheckInEntityByDateAndUsername(LocalDate date, String username);

    List<CheckInEntity> findCheckInEntitiesByUsername(String username);

    List<CheckInEntity> findCheckInEntitiesByDate(LocalDate date);

    boolean existsCheckInEntityByDateAndUsername(LocalDate date, String username);

    List<CheckInEntity> findCheckInEntitiesByUsernameAndDateBetween(String username, LocalDate startDate, LocalDate endDate);
}
