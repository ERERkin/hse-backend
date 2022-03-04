package kz.ccecc.hse_backend.mapper.batteryChargingMapper;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingPollutionSourceDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearLimitDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingPollutionSource;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BatteryChargingPollutionSourceToBatteryChargingPollutionSourceDtoMapper
        extends AbstractMapper<BatteryChargingPollutionSource, BatteryChargingPollutionSourceDto> {
    public BatteryChargingPollutionSourceToBatteryChargingPollutionSourceDtoMapper(ModelMapper mapper) {
        super(mapper, BatteryChargingPollutionSource.class, BatteryChargingPollutionSourceDto.class);
    }

    @Override
    public BatteryChargingPollutionSourceDto toDto(BatteryChargingPollutionSource entity) {
        BatteryChargingPollutionSourceDto batteryChargingPollutionSourceDto = super.toDto(entity);
        if(Objects.isNull(batteryChargingPollutionSourceDto.getYearLimits())) return batteryChargingPollutionSourceDto;
        List<BatteryChargingYearLimitDto> yearLimitList = batteryChargingPollutionSourceDto.getYearLimits().stream().peek(yearLimitDto -> {
            yearLimitDto.setPollutionSource(null);
        }).collect(Collectors.toList());
        batteryChargingPollutionSourceDto.setYearLimits(yearLimitList);
        return batteryChargingPollutionSourceDto;
    }
}
