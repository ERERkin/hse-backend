package kz.ccecc.hse_backend.service.fuelCombustionService.impl;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionMothDataDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionYearLimitDto;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionYearLimit;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionYearLimitDtoMapper;
import kz.ccecc.hse_backend.repository.fuelCombustionRepository.FuelCombustionYearLimitRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionMothDataService;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionYearLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FuelCombustionYearLimitServiceImpl extends AbstractService<FuelCombustionYearLimit,
        FuelCombustionYearLimitDto,
        FuelCombustionYearLimitRepository,
        FuelCombustionYearLimitDtoMapper> implements FuelCombustionYearLimitService {
    public FuelCombustionYearLimitServiceImpl(FuelCombustionYearLimitRepository repository, FuelCombustionYearLimitDtoMapper mapper) {
        super(repository, mapper, "fuelCombustionYearLimit");
    }

    @Autowired
    FuelCombustionMothDataService fuelCombustionMothDataService;

    @Override
    public FuelCombustionYearLimitDto save(FuelCombustionYearLimitDto item) {
        if (Objects.nonNull(item.getWorkTime()) &&
                Objects.nonNull(item.getConsumptionM3OnYear()) &&
                Objects.isNull(item.getConsumptionTonOnYear()) &&
                Objects.nonNull(item.getFuelType().getFuelDensity())) {
            BigDecimal answer = item.getConsumptionM3OnYear().multiply(item.getFuelType().getFuelDensity());
            answer = answer.divide(BigDecimal.valueOf(1000L), 5, RoundingMode.CEILING);
            item.setConsumptionTonOnYear(answer);
        } else if (Objects.nonNull(item.getConsumptionKgOnYear()) &&
                    Objects.isNull(item.getConsumptionTonOnYear())) {
            BigDecimal answer = item.getConsumptionKgOnYear().divide(BigDecimal.valueOf(1000000L), 5, RoundingMode.CEILING);
            item.setConsumptionTonOnYear(answer);
        }
        FuelCombustionYearLimitDto yearLimitSaved =  super.save(item);
        if (Objects.isNull(item.getMothDataList())) return yearLimitSaved;
        List<FuelCombustionMothDataDto> mothDataDtoList = new ArrayList<>();
        item.getMothDataList().forEach(fuelCombustionMothDataDto -> {
            fuelCombustionMothDataDto.setYearLimit(yearLimitSaved);
            FuelCombustionMothDataDto mothDataSaved = fuelCombustionMothDataService.save(fuelCombustionMothDataDto);
            mothDataSaved.setYearLimit(null);
            mothDataDtoList.add(mothDataSaved);
        });
        yearLimitSaved.setMothDataList(mothDataDtoList);
//        if(Objects.nonNull(FuelCombustionYearLimitSaved.getId()) &&
//                Objects.nonNull(FuelCombustionYearLimitSaved.getYear())){
//            List<FuelCombustionQuarterDataDto> FuelCombustionQuarterDataDtoList = new ArrayList<>();
//            for(int i = 1; i <= 4; i++){
//                FuelCombustionQuarterDataDto fuelCombustionQuarterDataDto = fuelCombustionMothDataService.getQuarterDateByYearAndQuarterNum(
//                        FuelCombustionYearLimitSaved.getId(),
//                        FuelCombustionYearLimitSaved.getYear(),
//                        (long) i);
//                if(Objects.nonNull(fuelCombustionQuarterDataDto))
//                    FuelCombustionQuarterDataDtoList.add(fuelCombustionQuarterDataDto);
//            }
//            yearLimitSaved.setQuarterDataList(FuelCombustionQuarterDataDtoList);
//        }
        return yearLimitSaved;
    }
}
