package za.co.wethinkcode.dms.absenteeismFeature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.wethinkcode.dms.absenteeismFeature.entities.CheckInEntity;
import za.co.wethinkcode.dms.absenteeismFeature.model.CheckIn;
import za.co.wethinkcode.dms.absenteeismFeature.model.CheckOut;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CheckInRepository extends JpaRepository<CheckInEntity, String> {

    Optional<CheckInEntity> findCheckInEntityByDate(LocalDate date);

    List<CheckInEntity> getCheckInEntityByUsername(String username);
}
