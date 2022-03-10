package kz.ccecc.hse_backend.service.fuelCombustionService.impl;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionMothDataDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionYearLimitDto;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionMothData;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionMothDataDtoMapper;
import kz.ccecc.hse_backend.repository.fuelCombustionRepository.FuelCombustionMothDataRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionMothDataService;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionYearLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Year;
import java.util.Objects;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FuelCombustionMothDataServiceImpl extends AbstractService<FuelCombustionMothData,
        FuelCombustionMothDataDto,
        FuelCombustionMothDataRepository,
        FuelCombustionMothDataDtoMapper> implements FuelCombustionMothDataService {
    public FuelCombustionMothDataServiceImpl(FuelCombustionMothDataRepository repository, FuelCombustionMothDataDtoMapper mapper) {
        super(repository, mapper, "fuelCombustionMothData");
    }

    @Override
    public FuelCombustionMothDataDto save(FuelCombustionMothDataDto item) {
        if (Objects.nonNull(item.getWorkTime()) &&
                Objects.nonNull(item.getConsumptionM3OnMonth()) &&
                Objects.isNull(item.getConsumptionTonOnMonth()) &&
                Objects.nonNull(item.getYearLimit().getFuelType().getFuelDensity())) {
            BigDecimal answer = item.getConsumptionM3OnMonth().multiply(item.getYearLimit().getFuelType().getFuelDensity());
            answer = answer.divide(BigDecimal.valueOf(1000L), 5, RoundingMode.CEILING);
            item.setConsumptionTonOnMonth(answer);
        }else if(Objects.nonNull(item.getWorkTime()) &&
                Objects.nonNull(item.getConsumptionLiterOnMonth()) &&
                Objects.isNull(item.getConsumptionTonOnMonth()) &&
                Objects.nonNull(item.getYearLimit().getFuelType().getFuelDensity())) {
            BigDecimal answer = item.getConsumptionLiterOnMonth().multiply(item.getYearLimit().getFuelType().getFuelDensity());
            answer = answer.divide(BigDecimal.valueOf(1000000L), 5, RoundingMode.CEILING);
            item.setConsumptionTonOnMonth(answer);
        } else if (Objects.nonNull(item.getConsumptionKgOnMonth()) &&
                Objects.isNull(item.getConsumptionTonOnMonth())) {
            BigDecimal answer = item.getConsumptionKgOnMonth().divide(BigDecimal.valueOf(1000L), 5, RoundingMode.CEILING);
            item.setConsumptionTonOnMonth(answer);
        }
        return super.save(item);
    }
}
