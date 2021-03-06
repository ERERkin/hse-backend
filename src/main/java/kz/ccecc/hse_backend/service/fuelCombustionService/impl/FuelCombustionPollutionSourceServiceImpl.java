package kz.ccecc.hse_backend.service.fuelCombustionService.impl;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionFuelTypeDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionPollutionSourceDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionYearLimitDto;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionPollutionSource;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionFuelTypeDtoMapper;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionPollutionSourceDtoMapper;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionYearLimitDtoMapper;
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
public class FuelCombustionPollutionSourceServiceImpl extends AbstractService<FuelCombustionPollutionSource,
        FuelCombustionPollutionSourceDto,
        FuelCombustionPollutionSourceRepository,
        FuelCombustionPollutionSourceDtoMapper> implements FuelCombustionPollutionSourceService {
    public FuelCombustionPollutionSourceServiceImpl(FuelCombustionPollutionSourceRepository repository, FuelCombustionPollutionSourceDtoMapper mapper) {
        super(repository, mapper, "fuelCombustionPollutionSource");
    }

    @Autowired
    FuelCombustionFuelTypeDtoMapper fuelCombustionFuelTypeDtoMapper;
    @Autowired
    FuelCombustionFuelTypeService fuelCombustionFuelTypeService;

    @Override
    public FuelCombustionPollutionSourceDto save(FuelCombustionPollutionSourceDto item) {
        FuelCombustionPollutionSourceDto pollutionSourceSaved = super.save(item);
        if(Objects.isNull(item.getFuelTypes())) return pollutionSourceSaved;
        List<FuelCombustionFuelTypeDto> fuelTypeDtoList = new ArrayList<>();
        item.getFuelTypes().forEach(fuelTypeDto -> {
            fuelTypeDto.setPollutionSource(pollutionSourceSaved);
            FuelCombustionFuelTypeDto fuelTypeDtoSaved = fuelCombustionFuelTypeService.save(fuelTypeDto);
            fuelTypeDtoSaved.setPollutionSource(null);
            fuelTypeDtoList.add(fuelTypeDtoSaved);
        });
        pollutionSourceSaved.setFuelTypes(fuelTypeDtoList);
        return pollutionSourceSaved;
    }
}
