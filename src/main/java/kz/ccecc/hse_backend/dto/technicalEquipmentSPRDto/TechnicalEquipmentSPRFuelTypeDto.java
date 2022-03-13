package kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto;

import io.swagger.annotations.ApiModel;
import kz.ccecc.hse_backend.dto.base.AbstractDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRPollutionSource;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRYearLimit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@SuperBuilder
@ApiModel(value = "ППР тех. обрудования  (стравливание газа ) | TechnicalEquipmentSPRFuelTypeDto", description = "объект данных о виде топлива")
public class TechnicalEquipmentSPRFuelTypeDto extends AbstractDto {
    String fuelType;
    BigDecimal fuelDensity;
    TechnicalEquipmentSPRPollutionSource pollutionSource;
    List<TechnicalEquipmentSPRYearLimit> yearLimits;
}
