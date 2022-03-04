package kz.ccecc.hse_backend.mapper.batteryChargingMapper;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingPollutionSourceDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingProductionDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingProduction;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BatteryChargingProductionToBatteryChargingProductionDtoMapper
        extends AbstractMapper<BatteryChargingProduction, BatteryChargingProductionDto> {
    public BatteryChargingProductionToBatteryChargingProductionDtoMapper(ModelMapper mapper) {
        super(mapper, BatteryChargingProduction.class, BatteryChargingProductionDto.class);
    }

    @Override
    public BatteryChargingProductionDto toDto(BatteryChargingProduction entity) {
        BatteryChargingProductionDto batteryChargingProductionDto = super.toDto(entity);
        if(Objects.isNull(batteryChargingProductionDto.getPollutionSources())) return batteryChargingProductionDto;
        List<BatteryChargingPollutionSourceDto> batteryChargingPollutionSourceDtos = batteryChargingProductionDto.getPollutionSources().stream().peek(pollutionSourceDto -> {
            pollutionSourceDto.setProduction(null);
        }).collect(Collectors.toList());
        batteryChargingProductionDto.setPollutionSources(batteryChargingPollutionSourceDtos);
        return batteryChargingProductionDto;
    }
}
