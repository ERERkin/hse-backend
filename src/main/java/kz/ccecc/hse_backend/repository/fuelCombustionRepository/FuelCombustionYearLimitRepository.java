package kz.ccecc.hse_backend.repository.fuelCombustionRepository;

import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionYearLimit;
import kz.ccecc.hse_backend.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelCombustionYearLimitRepository extends BaseRepository<FuelCombustionYearLimit> {
//    @Query(value = "from FuelCombustionYearLimit fcyl where fcyl.year = :year")
//    List<FuelCombustionYearLimit> getFuelCombustionYearLimitByYear(Long year);
}
