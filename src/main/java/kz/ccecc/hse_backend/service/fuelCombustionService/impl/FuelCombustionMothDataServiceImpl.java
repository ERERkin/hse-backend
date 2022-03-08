package kz.ccecc.hse_backend.service.fuelCombustionService.impl;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionMothDataDto;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionMothData;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionMothDataDtoMapper;
import kz.ccecc.hse_backend.repository.fuelCombustionRepository.FuelCombustionMothDataRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionMothDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FuelCombustionMothDataServiceImpl extends AbstractService<FuelCombustionMothData,
        FuelCombustionMothDataDto,
        FuelCombustionMothDataRepository,
        FuelCombustionMothDataDtoMapper> implements FuelCombustionMothDataService {
    public FuelCombustionMothDataServiceImpl(FuelCombustionMothDataRepository repository, FuelCombustionMothDataDtoMapper mapper) {
        super(repository, mapper, "fuelCombustionMothData");
    }
}
