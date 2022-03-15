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

@Component
@Qualifier("modelMapper")
public class BatteryChargingPollutionSourceDtoMapper
        extends AbstractMapper<BatteryChargingPollutionSource, BatteryChargingPollutionSourceDto> {
    public BatteryChargingPollutionSourceDtoMapper(ModelMapper mapper) {
        super(mapper, BatteryChargingPollutionSource.class, BatteryChargingPollutionSourceDto.class);
    }

    @Autowired
    BatteryChargingYearLimitDtoMapper batteryChargingYearLimitDtoMapper;

    @Override
    public BatteryChargingPollutionSourceDto toDto(BatteryChargingPollutionSource entity) {
        List<BatteryChargingYearLimitDto> yearLimitList =
                batteryChargingYearLimitDtoMapper.toDtos(entity.getYearLimits());
        BatteryChargingPollutionSourceDto batteryChargingPollutionSourceDto = super.toDto(entity);
        batteryChargingPollutionSourceDto.setYearLimits(yearLimitList);
        return batteryChargingPollutionSourceDto;
    }
}
