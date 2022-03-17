package kz.ccecc.hse_backend.dto.fuelCombustionDto;

import io.swagger.annotations.ApiModel;
import kz.ccecc.hse_backend.dto.base.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@SuperBuilder
@ApiModel(value = "Сгорание топлива | FuelCombustionQuarterDataDto", description = "объект данных за квартал")
public class FuelCombustionQuarterDataDto extends AbstractDto {
    String quarter;
    BigDecimal workTime;
    BigDecimal consumptionM3OnMonth;
    BigDecimal consumptionLiterOnMonth;
    BigDecimal consumptionKgOnMonth;
    BigDecimal consumptionTonOnMonth;
    BigDecimal consumptionM3OnMonthLimit;
    BigDecimal consumptionKgOnMonthLimit;
    BigDecimal consumptionTonOnMonthLimit;
    FuelCombustionYearLimitDto yearLimit;
}
