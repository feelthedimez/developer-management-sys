package za.co.wethinkcode.dms.absenteeismFeature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.wethinkcode.dms.absenteeismFeature.model.CheckIn;
import za.co.wethinkcode.dms.absenteeismFeature.model.CheckOut;

public interface CheckInRepository extends JpaRepository<CheckIn, String> {
}
