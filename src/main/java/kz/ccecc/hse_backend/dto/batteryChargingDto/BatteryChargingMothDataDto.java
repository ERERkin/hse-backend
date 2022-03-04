package kz.ccecc.hse_backend.dto.batteryChargingDto;

import kz.ccecc.hse_backend.dto.base.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@SuperBuilder
public class BatteryChargingMothDataDto extends AbstractDto {
    String month;
    Double workTime;
    Long batteryCount;
    Double batteryCapacity;
    BatteryChargingYearLimitDto yearLimit;
}
