package kz.ccecc.hse_backend.repository.technicalEquipmentSPRRepository;

import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionYearLimit;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRYearLimit;
import kz.ccecc.hse_backend.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TechnicalEquipmentSPRYearLimitRepository extends BaseRepository<TechnicalEquipmentSPRYearLimit> {
    @Query(value = "from TechnicalEquipmentSPRYearLimit te where te.year = :year")
    List<TechnicalEquipmentSPRYearLimit> getTechnicalEquipmentSPRYearLimitByYear(Long year);
}
