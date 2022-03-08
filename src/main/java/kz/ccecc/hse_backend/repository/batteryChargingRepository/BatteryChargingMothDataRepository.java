package kz.ccecc.hse_backend.repository.batteryChargingRepository;

import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingMothData;
import kz.ccecc.hse_backend.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BatteryChargingMothDataRepository extends BaseRepository<BatteryChargingMothData> {
    @Query(value = "from FuelCombustionMothDataDto bcmd where bcmd.yearLimit.id = :yearLimitId and bcmd.month BETWEEN :startDate AND :endDate")
    List<BatteryChargingMothData> getBatteryChargingMothDataByDateQuarter(@Param("yearLimitId") Long yearLimitId,
                                                                          @Param("startDate") LocalDate startDate,
                                                                          @Param("endDate")LocalDate endDate);
}
