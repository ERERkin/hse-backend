package kz.ccecc.hse_backend.mapper.fuelCombustionMapper;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionMothDataDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionYearLimitDto;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionYearLimit;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FuelCombustionYearLimitDtoMapper
        extends AbstractMapper<FuelCombustionYearLimit, FuelCombustionYearLimitDto> {
    public FuelCombustionYearLimitDtoMapper(ModelMapper mapper) {
        super(mapper, FuelCombustionYearLimit.class, FuelCombustionYearLimitDto.class);
    }

    @Autowired
    FuelCombustionMothDataDtoMapper fuelCombustionMothDataDtoMapper;

    @Override
    public FuelCombustionYearLimitDto toDto(FuelCombustionYearLimit entity) {
        List<FuelCombustionMothDataDto> fuelCombustionMothDataDtoList =
                fuelCombustionMothDataDtoMapper.toDtos(entity.getMothDataList());
        FuelCombustionYearLimitDto fuelCombustionYearLimitDto = super.toDto(entity);
        fuelCombustionYearLimitDto.setMothDataList(fuelCombustionMothDataDtoList);
        return fuelCombustionYearLimitDto;
    }
}
