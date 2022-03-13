package kz.ccecc.hse_backend.dto.fuelCombustionDto;

import io.swagger.annotations.ApiModel;
import kz.ccecc.hse_backend.dto.base.AbstractDto;
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
@ApiModel(value = "Сгорание топлива | BatteryChargingMothDataDto", description = "объект данных  о виде топлива")
public class FuelCombustionFuelTypeDto extends AbstractDto {
    String fuelType;
    BigDecimal fuelDensity;
    FuelCombustionPollutionSourceDto pollutionSource;
    List<FuelCombustionYearLimitDto> yearLimits;
}
