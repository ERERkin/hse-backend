package kz.ccecc.hse_backend.mapper.fuelCombustionMapper;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionPollutionSourceDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionProductionDto;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionProduction;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FuelCombustionProductionDtoMapper
        extends AbstractMapper<FuelCombustionProduction, FuelCombustionProductionDto> {
    public FuelCombustionProductionDtoMapper(ModelMapper mapper) {
        super(mapper, FuelCombustionProduction.class, FuelCombustionProductionDto.class);
    }

    @Autowired
    FuelCombustionPollutionSourceDtoMapper fuelCombustionPollutionSourceDtoMapper;

    @Override
    public FuelCombustionProductionDto toDto(FuelCombustionProduction entity) {
        List<FuelCombustionPollutionSourceDto> fuelCombustionPollutionSourceDtoList =
                fuelCombustionPollutionSourceDtoMapper.toDtos(entity.getPollutionSources());
        FuelCombustionProductionDto fuelCombustionProductionDto = super.toDto(entity);
        fuelCombustionProductionDto.setPollutionSources(fuelCombustionPollutionSourceDtoList);
        return fuelCombustionProductionDto;
    }
}
