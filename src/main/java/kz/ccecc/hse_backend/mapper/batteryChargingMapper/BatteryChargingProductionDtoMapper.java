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
@Qualifier("modelMapper")
public class BatteryChargingProductionDtoMapper
        extends AbstractMapper<BatteryChargingProduction, BatteryChargingProductionDto> {
    public BatteryChargingProductionDtoMapper(ModelMapper mapper) {
        super(mapper, BatteryChargingProduction.class, BatteryChargingProductionDto.class);
    }

    @Autowired
    BatteryChargingPollutionSourceDtoMapper batteryChargingPollutionSourceDtoMapper;

    @Override
    public BatteryChargingProductionDto toDto(BatteryChargingProduction entity) {
        List<BatteryChargingPollutionSourceDto> batteryChargingPollutionSourceDtoList =
                batteryChargingPollutionSourceDtoMapper.toDtos(entity.getPollutionSources());
        BatteryChargingProductionDto batteryChargingProductionDto = super.toDto(entity);
        batteryChargingProductionDto.setPollutionSources(batteryChargingPollutionSourceDtoList);
        return batteryChargingProductionDto;
    }
}
