package za.co.wethinkcode.dms.absenteeismFeature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.wethinkcode.dms.absenteeismFeature.entities.CheckInEntity;
import za.co.wethinkcode.dms.absenteeismFeature.model.CheckIn;
import za.co.wethinkcode.dms.absenteeismFeature.repository.CheckInRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CheckInService {

    private final CheckInRepository checkInRepository;

    public CheckInService(@Autowired CheckInRepository checkInRepository) {
        this.checkInRepository = checkInRepository;
    }

    public void addACheckIn(String username,LocalTime time, LocalDate date) {
        CheckInEntity checkIn = new CheckInEntity(username, time, date);
        checkInRepository.save(checkIn);
    }

    public Optional<CheckInEntity> getCheckInDataByDate(LocalDate date) {
        return checkInRepository.findCheckInEntityByDate(date);
    }

    public List<CheckInEntity> getAllCheckInDataByUserName(String username) {
        return checkInRepository.getCheckInEntityByUsername(username);
    }

}
