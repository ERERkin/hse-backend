package kz.ccecc.hse_backend.mapper.batteryChargingMapper;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingPollutionSourceDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingProductionDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingProduction;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("modelMapperFull")
public class BatteryChargingProductionToBatteryChargingProductionDtoMapper
        extends AbstractMapper<BatteryChargingProduction, BatteryChargingProductionDto> {
    public BatteryChargingProductionToBatteryChargingProductionDtoMapper(@Qualifier("modelMapper") ModelMapper mapper) {
        super(mapper, BatteryChargingProduction.class, BatteryChargingProductionDto.class);
    }

    @Autowired
    BatteryChargingPollutionSourceToBatteryChargingPollutionSourceDtoMapper batteryChargingPollutionSourceToBatteryChargingPollutionSourceDtoMapper;

    @Override
    public BatteryChargingProductionDto toDto(BatteryChargingProduction entity) {
        List<BatteryChargingPollutionSourceDto> batteryChargingPollutionSourceDtoList =
                batteryChargingPollutionSourceToBatteryChargingPollutionSourceDtoMapper.toDtos(entity.getPollutionSources());
        BatteryChargingProductionDto batteryChargingProductionDto = super.toDto(entity);
        batteryChargingProductionDto.setPollutionSources(batteryChargingPollutionSourceDtoList);
        return batteryChargingProductionDto;
    }
}
