package za.co.wethinkcode.dms.checkInAndOutSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckInEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.CustomErrorWithDataException;
import za.co.wethinkcode.dms.checkInAndOutSystem.repository.CheckInRepository;

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

    public void addACheckIn(String username, LocalTime time, LocalDate date, String phoneNumber) {

        CheckInEntity checkInEntity;
        try {
            checkInEntity = new CheckInEntity(username, phoneNumber, time, date);
        } catch (Exception e) {
            throw new CustomErrorWithDataException(e.getMessage());
        }

        checkInRepository.save(checkInEntity);
    }

    public Optional<CheckInEntity> getCheckInDataByDateAndUserName(LocalDate date, String username) {
        return checkInRepository.findCheckInEntityByDateAndUsername(date, username);
    }

    public List<CheckInEntity> getAllCheckInDataByUserName(String username) {
        return checkInRepository.findCheckInEntitiesByUsername(username);
    }

    public boolean doesDateAndUsernameExist(LocalDate date, String username) {
        return checkInRepository.existsCheckInEntityByDateAndUsername(date, username);
    }

    public List<CheckInEntity> getAllCheckInDataByDate(LocalDate date) {
        return checkInRepository.findCheckInEntitiesByDate(date);
    }

    public List<CheckInEntity> getAllCheckIn() {
        return checkInRepository.findAll();
    }

    public List<CheckInEntity> getAllCheckInBetweenDates(String username, LocalDate startDate, LocalDate endDate) {
        return checkInRepository.findCheckInEntitiesByUsernameAndDateBetween(username, startDate, endDate);
    }

}