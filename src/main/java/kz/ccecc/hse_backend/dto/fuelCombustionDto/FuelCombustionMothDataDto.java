package kz.ccecc.hse_backend.dto.fuelCombustionDto;

import io.swagger.annotations.ApiModel;
import kz.ccecc.hse_backend.dto.base.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@SuperBuilder
@ApiModel(value = "Сгорание топлива | BatteryChargingMothDataDto", description = "объект данных за месяц")
public class FuelCombustionMothDataDto extends AbstractDto {
    String month;
    BigDecimal workTime;
    BigDecimal consumptionM3OnMonth;
    BigDecimal consumptionLiterOnMonth;
    BigDecimal consumptionKgOnMonth;
    BigDecimal consumptionTonOnMonth;
    FuelCombustionYearLimitDto yearLimit;
}
