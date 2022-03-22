package za.co.wethinkcode.dms.checkInAndOutSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckOutEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.CustomErrorWithDataException;
import za.co.wethinkcode.dms.checkInAndOutSystem.repository.CheckOutRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CheckOutService {

    private final CheckOutRepository checkOutRepository;

    public CheckOutService(@Autowired CheckOutRepository checkOutRepository) {
        this.checkOutRepository = checkOutRepository;
    }

    public void addCheckOut(String username, LocalTime time, LocalDate date) {

        CheckOutEntity checkOutEntity;
        try {
            checkOutEntity = new CheckOutEntity(username, time, date);
        } catch (Exception e) {
            throw new CustomErrorWithDataException(e.getMessage());
        }

        checkOutRepository.save(checkOutEntity);
    }

    public Optional<CheckOutEntity> getCheckOutDataByDateAndUserName(LocalDate date, String username) {
        return checkOutRepository.findCheckOutEntityByDateAndUsername(date, username);
    }

    public List<CheckOutEntity> getAllCheckOutDataByUserName(String username) {
        return checkOutRepository.findCheckOutEntitiesByUsername(username);
    }

    public boolean doesDateAndUsernameExist(LocalDate date, String username) {
        return checkOutRepository.existsCheckInEntityByDateAndUsername(date, username);
    }
}
