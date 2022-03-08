package kz.ccecc.hse_backend.mapper.fuelCombustionMapper;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionPollutionSourceDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionYearLimitDto;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionPollutionSource;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FuelCombustionPollutionSourceDtoMapper
        extends AbstractMapper<FuelCombustionPollutionSource, FuelCombustionPollutionSourceDto> {
    public FuelCombustionPollutionSourceDtoMapper(ModelMapper mapper) {
        super(mapper, FuelCombustionPollutionSource.class, FuelCombustionPollutionSourceDto.class);
    }

    @Autowired
    FuelCombustionYearLimitDtoMapper fuelCombustionYearLimitDtoMapper;

    @Override
    public FuelCombustionPollutionSourceDto toDto(FuelCombustionPollutionSource entity) {
        List<FuelCombustionYearLimitDto> fuelCombustionYearLimitDtoList =
                fuelCombustionYearLimitDtoMapper.toDtos(entity.getYearLimits());
        FuelCombustionPollutionSourceDto fuelCombustionPollutionSourceDto = super.toDto(entity);
        fuelCombustionPollutionSourceDto.setYearLimits(fuelCombustionYearLimitDtoList);
        return fuelCombustionPollutionSourceDto;
    }
}
