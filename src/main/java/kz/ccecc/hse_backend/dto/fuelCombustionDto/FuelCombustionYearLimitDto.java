package kz.ccecc.hse_backend.dto.fuelCombustionDto;

import io.swagger.annotations.ApiModel;
import kz.ccecc.hse_backend.dto.base.AbstractDto;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionPollutionSource;
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
@ApiModel(value = "Сгорание топлива | BatteryChargingMothDataDto", description = "объект данных о лимите")
public class FuelCombustionYearLimitDto extends AbstractDto {
    Long year;
    BigDecimal workTime;
    BigDecimal consumptionM3OnYear;
    BigDecimal consumptionKgOnYear;
    BigDecimal consumptionTonOnYear;
    FuelCombustionFuelTypeDto fuelType;
    List<FuelCombustionMothDataDto> mothDataList;
}
