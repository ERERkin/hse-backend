package kz.ccecc.hse_backend.mapper.batteryChargingMapper;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearLimitDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingYearLimit;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Qualifier("modelMapper")
public class BatteryChargingYearLimitToBatteryChargingYearLimitDtoMapper
        extends AbstractMapper<BatteryChargingYearLimit, BatteryChargingYearLimitDto> {
    public BatteryChargingYearLimitToBatteryChargingYearLimitDtoMapper(ModelMapper mapper) {
        super(mapper, BatteryChargingYearLimit.class, BatteryChargingYearLimitDto.class);
    }

    @Override
    public BatteryChargingYearLimitDto toDto(BatteryChargingYearLimit entity) {
        BatteryChargingYearLimitDto batteryChargingYearLimitDto = super.toDto(entity);
        if(Objects.isNull(batteryChargingYearLimitDto.getMothDataList())) return batteryChargingYearLimitDto;
        List<BatteryChargingMothDataDto> batteryChargingMothDataDtoList = batteryChargingYearLimitDto.getMothDataList().stream().peek(batteryChargingMothDataDto -> {
            batteryChargingMothDataDto.setYearLimit(null);
        }).collect(Collectors.toList());
        batteryChargingYearLimitDto.setMothDataList(batteryChargingMothDataDtoList);
        return batteryChargingYearLimitDto;
    }
}
