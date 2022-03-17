package kz.ccecc.hse_backend.dto.fuelCombustionDto;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "Сгорание топлива | FuelCombustionTotalDataDto", description = "объект данных итоги")
public class FuelCombustionTotalDataResponse {
    String category;
    List<FuelCombustionMothDataDto> mothDataDtoList;
    List<FuelCombustionQuarterDataDto> quarterDataDtoList;
    FuelCombustionYearDataDto yearData;
    FuelCombustionYearLimitDto yearLimit;
}
