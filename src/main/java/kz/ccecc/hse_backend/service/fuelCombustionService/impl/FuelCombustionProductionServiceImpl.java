package kz.ccecc.hse_backend.service.fuelCombustionService.impl;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionPollutionSourceDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionProductionDto;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionProduction;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionPollutionSourceDtoMapper;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionProductionDtoMapper;
import kz.ccecc.hse_backend.repository.fuelCombustionRepository.FuelCombustionProductionRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionPollutionSourceService;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FuelCombustionProductionServiceImpl extends AbstractService<FuelCombustionProduction,
        FuelCombustionProductionDto,
        FuelCombustionProductionRepository,
        FuelCombustionProductionDtoMapper> implements FuelCombustionProductionService {
    public FuelCombustionProductionServiceImpl(FuelCombustionProductionRepository repository, FuelCombustionProductionDtoMapper mapper) {
        super(repository, mapper, "fuelCombustionProduction");
    }

    @Autowired
    FuelCombustionPollutionSourceDtoMapper fuelCombustionPollutionSourceDtoMapper;
    @Autowired
    FuelCombustionPollutionSourceService fuelCombustionPollutionSourceService;

    @Override
    public FuelCombustionProductionDto save(FuelCombustionProductionDto item) {
        FuelCombustionProductionDto productionSaved = super.save(item);
        if(Objects.isNull(productionSaved.getPollutionSources())) return productionSaved;
        List<FuelCombustionPollutionSourceDto> pollutionSources = new ArrayList<>();
        item.getPollutionSources().forEach(pollutionSourceDto -> {
            pollutionSourceDto.setProduction(productionSaved);
            FuelCombustionPollutionSourceDto pollutionSource = fuelCombustionPollutionSourceService.save(pollutionSourceDto);
            pollutionSource.setProduction(null);
            pollutionSources.add(pollutionSource);
        });

        productionSaved.setPollutionSources(pollutionSources);
        return productionSaved;
    }
}
