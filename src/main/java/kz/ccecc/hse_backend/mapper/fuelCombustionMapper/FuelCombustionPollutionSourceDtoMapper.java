package kz.ccecc.hse_backend.mapper.fuelCombustionMapper;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionFuelTypeDto;
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
    FuelCombustionFuelTypeDtoMapper fuelCombustionFuelTypeDtoMapper;

    @Override
    public FuelCombustionPollutionSourceDto toDto(FuelCombustionPollutionSource entity) {
        List<FuelCombustionFuelTypeDto> fuelTypeDtoList =
                fuelCombustionFuelTypeDtoMapper.toDtos(entity.getFuelTypes());
        FuelCombustionPollutionSourceDto fuelCombustionPollutionSourceDto = super.toDto(entity);
        fuelCombustionPollutionSourceDto.setFuelTypes(fuelTypeDtoList);
        return fuelCombustionPollutionSourceDto;
    }
}
