package za.co.wethinkcode.dms.checkInAndOutSystem.repository;

import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckInEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckOutEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface CheckOutRepository extends JpaRepository<CheckOutEntity, String> {

    Optional<CheckOutEntity> findCheckOutEntityByDateAndUsername(LocalDate date, String username);

    List<CheckOutEntity> findCheckOutEntitiesByUsername(String username);

    boolean existsCheckInEntityByDateAndUsername(LocalDate date, String username);

    int countCheckOutEntitiesByDateBetween(LocalDate startDate, LocalDate endDate);

    List<CheckOutEntity> findCheckOutEntitiesByUsernameAndDateBetween(String username, LocalDate startDate, LocalDate endDate);

//    Query("select abs(datediff('?1 ?3', '?2 ?4'))")
    double hoursThings(@Param(value = "start_date") LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime);
}
