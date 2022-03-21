package za.co.wethinkcode.dms.checkInAndOutSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckInEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CheckInRepository extends JpaRepository<CheckInEntity, String> {

    Optional<CheckInEntity> findCheckInEntityByDate(LocalDate date);

    List<CheckInEntity> getCheckInEntityByUsername(String username);
}