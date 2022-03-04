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
public class BatteryChargingProductionDto extends AbstractDto {
    String name;
    List<BatteryChargingPollutionSourceDto> pollutionSources;
}
