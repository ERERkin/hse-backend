package kz.ccecc.hse_backend.dto.batteryChargingDto;

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
public class BatteryChargingPollutionSourceDto extends AbstractDto {
    String name;
    String number;
    BatteryChargingProductionDto production;
    List<BatteryChargingYearLimitDto> yearLimits;
}
