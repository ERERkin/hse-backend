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
@ApiModel(value = "Сгорание топлива | FuelCombustionPollutionSourceDto", description = "объект данных об источние выброса")
public class FuelCombustionPollutionSourceDto extends AbstractDto {
    String name;
    String number;
    FuelCombustionProductionDto production;
    List<FuelCombustionFuelTypeDto> fuelTypes;
}
