package kz.ccecc.hse_backend.dto.fuelCombustionDto;

import io.swagger.annotations.ApiModel;
import kz.ccecc.hse_backend.dto.base.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@SuperBuilder
@ApiModel(value = "Сгорание топлива | FuelCombustionTotalDataDto", description = "объект данных итоги")
public class FuelCombustionTotalDataDto extends AbstractDto {
    String category;
    HashMap<String, FuelCombustionMothDataDto> mothDataDtoHashMap;
    HashMap<String, FuelCombustionQuarterDataDto> quarterDataDtoHashMap;
    FuelCombustionYearDataDto yearData;
}
