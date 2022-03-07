package kz.ccecc.hse_backend.mapper.batteryChargingMapper;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingPollutionSourceDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearLimitDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingPollutionSource;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BatteryChargingPollutionSourceToBatteryChargingPollutionSourceDtoMapper
        extends AbstractMapper<BatteryChargingPollutionSource, BatteryChargingPollutionSourceDto> {
    public BatteryChargingPollutionSourceToBatteryChargingPollutionSourceDtoMapper(@Qualifier("modelMapper") ModelMapper mapper) {
        super(mapper, BatteryChargingPollutionSource.class, BatteryChargingPollutionSourceDto.class);
    }

    @Autowired
    BatteryChargingYearLimitToBatteryChargingYearLimitDtoMapper batteryChargingYearLimitToBatteryChargingYearLimitDtoMapper;

    @Override
    public BatteryChargingPollutionSourceDto toDto(BatteryChargingPollutionSource entity) {
        List<BatteryChargingYearLimitDto> yearLimitList =
                batteryChargingYearLimitToBatteryChargingYearLimitDtoMapper.toDtos(entity.getYearLimits());
        BatteryChargingPollutionSourceDto batteryChargingPollutionSourceDto = super.toDto(entity);
        batteryChargingPollutionSourceDto.setYearLimits(yearLimitList);
        return batteryChargingPollutionSourceDto;
    }
}
