package kz.ccecc.hse_backend.dto.batteryChargingDto;

import kz.ccecc.hse_backend.dto.base.AbstractDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingMothData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@SuperBuilder
public class BatteryChargingYearLimitDto extends AbstractDto {
    String batteryModel;
    Double workTime;
    Long batteryCount;
    Double batteryCapacity;
    BatteryChargingPollutionSourceDto pollutionSource;
    List<BatteryChargingMothDataDto> mothDataList;
}
