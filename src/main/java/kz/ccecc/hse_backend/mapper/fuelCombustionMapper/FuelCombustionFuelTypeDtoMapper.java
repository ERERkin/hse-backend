package kz.ccecc.hse_backend.mapper.fuelCombustionMapper;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionFuelTypeDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionYearLimitDto;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionFuelType;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("modelMapper")
public class FuelCombustionFuelTypeDtoMapper
    extends AbstractMapper<FuelCombustionFuelType, FuelCombustionFuelTypeDto> {
    public FuelCombustionFuelTypeDtoMapper(ModelMapper mapper) {
        super(mapper, FuelCombustionFuelType.class, FuelCombustionFuelTypeDto.class);
    }

    @Autowired
    FuelCombustionYearLimitDtoMapper fuelCombustionYearLimitDtoMapper;

    @Override
    public FuelCombustionFuelTypeDto toDto(FuelCombustionFuelType entity) {
        List<FuelCombustionYearLimitDto> fuelCombustionYearLimitDtoList =
                fuelCombustionYearLimitDtoMapper.toDtos(entity.getYearLimits());
        FuelCombustionFuelTypeDto fuelTypeDto = super.toDto(entity);
        fuelTypeDto.setYearLimits(fuelCombustionYearLimitDtoList);
        return fuelTypeDto;
    }
}
