package kz.ccecc.hse_backend.mapper.batteryChargingMapper;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearLimitDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingYearLimit;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BatteryChargingYearLimitToBatteryChargingYearLimitDtoMapper
        extends AbstractMapper<BatteryChargingYearLimit, BatteryChargingYearLimitDto> {
    public BatteryChargingYearLimitToBatteryChargingYearLimitDtoMapper(@Qualifier("modelMapper") ModelMapper mapper) {
        super(mapper, BatteryChargingYearLimit.class, BatteryChargingYearLimitDto.class);
    }

    @Autowired
    BatteryChargingMothDataToBatteryChargingMothDataDtoMapper batteryChargingMothDataToBatteryChargingMothDataDtoMapper;

    @Override
    public BatteryChargingYearLimitDto toDto(BatteryChargingYearLimit entity) {
        List<BatteryChargingMothDataDto> batteryChargingMothDataDtoList =
                batteryChargingMothDataToBatteryChargingMothDataDtoMapper
                        .toDtos(entity.getMothDataList());
        BatteryChargingYearLimitDto batteryChargingYearLimitDto = super.toDto(entity);
        batteryChargingYearLimitDto.setMothDataList(batteryChargingMothDataDtoList);
        return batteryChargingYearLimitDto;
    }
}
