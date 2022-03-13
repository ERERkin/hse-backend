package kz.ccecc.hse_backend.dto.fuelCombustionDto;

import io.swagger.annotations.ApiModel;
import kz.ccecc.hse_backend.dto.base.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@SuperBuilder
@ApiModel(value = "Сгорание топлива | BatteryChargingMothDataDto", description = "объект данных о производстве")
public class FuelCombustionProductionDto extends AbstractDto {
    String name;
    List<FuelCombustionPollutionSourceDto> pollutionSources;
}
