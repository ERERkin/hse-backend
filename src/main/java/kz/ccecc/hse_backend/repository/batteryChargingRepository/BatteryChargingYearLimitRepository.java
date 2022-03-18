package kz.ccecc.hse_backend.repository.batteryChargingRepository;

import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingYearLimit;
import kz.ccecc.hse_backend.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatteryChargingYearLimitRepository extends BaseRepository<BatteryChargingYearLimit> {
    @Query(value = "from BatteryChargingYearLimit bcyl where bcyl.year = :year")
    List<BatteryChargingYearLimit> getBatteryChargingYearLimitByYear(Long year);
}
