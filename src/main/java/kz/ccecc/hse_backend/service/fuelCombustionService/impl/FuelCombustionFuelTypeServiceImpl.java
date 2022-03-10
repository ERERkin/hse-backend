package kz.ccecc.hse_backend.service.fuelCombustionService.impl;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionFuelTypeDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionPollutionSourceDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionYearLimitDto;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionFuelType;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionPollutionSource;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionFuelTypeDtoMapper;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionPollutionSourceDtoMapper;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionYearLimitDtoMapper;
import kz.ccecc.hse_backend.repository.fuelCombustionRepository.FuelCombustionFuelTypeRepository;
import kz.ccecc.hse_backend.repository.fuelCombustionRepository.FuelCombustionPollutionSourceRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionFuelTypeService;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionPollutionSourceService;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionYearLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FuelCombustionFuelTypeServiceImpl extends AbstractService<FuelCombustionFuelType,
        FuelCombustionFuelTypeDto,
        FuelCombustionFuelTypeRepository,
        FuelCombustionFuelTypeDtoMapper> implements FuelCombustionFuelTypeService {
    public FuelCombustionFuelTypeServiceImpl(FuelCombustionFuelTypeRepository repository, FuelCombustionFuelTypeDtoMapper mapper) {
        super(repository, mapper, "fuelCombustionFuelType");
    }

    @Autowired
    FuelCombustionYearLimitDtoMapper fuelCombustionYearLimitDtoMapper;
    @Autowired
    FuelCombustionYearLimitService fuelCombustionYearLimitService;

    @Override
    public FuelCombustionFuelTypeDto save(FuelCombustionFuelTypeDto item) {
        FuelCombustionFuelTypeDto fuelTypeSaved = super.save(item);
        if(Objects.isNull(item.getYearLimits())) return fuelTypeSaved;
        List<FuelCombustionYearLimitDto> yearLimits = new ArrayList<>();
        item.getYearLimits().forEach(yearLimitDto -> {
            yearLimitDto.setFuelType(fuelTypeSaved);
            FuelCombustionYearLimitDto yearLimitSaved = fuelCombustionYearLimitService.save(yearLimitDto);
            yearLimitSaved.setFuelType(null);
            yearLimits.add(yearLimitSaved);
        });
        fuelTypeSaved.setYearLimits(yearLimits);
        return fuelTypeSaved;
    }
}
