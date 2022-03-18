package kz.ccecc.hse_backend.sheduler;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionYearLimitDto;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionYearLimit;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionYearLimitDtoMapper;
import kz.ccecc.hse_backend.repository.fuelCombustionRepository.FuelCombustionYearLimitRepository;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionYearLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FuelCombustionSheduler {
    @Autowired
    FuelCombustionYearLimitService fuelCombustionYearLimitService;
    @Autowired
    FuelCombustionYearLimitRepository fuelCombustionYearLimitRepository;
    @Autowired
    FuelCombustionYearLimitDtoMapper fuelCombustionYearLimitDtoMapper;

    //    @Scheduled(cron="0 0 0 25 12 ?") // it will run 25th December every year
    @Scheduled(cron = "0 0 0 1 1 ?") // it will run 1st January every year
    void createYearLimit(){
        List<FuelCombustionYearLimit> yearLimitList = fuelCombustionYearLimitRepository
                .getFuelCombustionYearLimitByYear(Long.parseLong("" + LocalDate.now().getYear()));

        List<FuelCombustionYearLimitDto> yearLimitDtoList = fuelCombustionYearLimitDtoMapper.toDtos(yearLimitList);

        yearLimitDtoList.forEach(yearLimitDto -> {
            fuelCombustionYearLimitService.save(FuelCombustionYearLimitDto.builder()
                    .year(yearLimitDto.getYear() + 1)
                    .workTime(yearLimitDto.getWorkTime())
                    .consumptionM3OnYear(yearLimitDto.getConsumptionM3OnYear())
                    .consumptionKgOnYear(yearLimitDto.getConsumptionKgOnYear())
                    .consumptionTonOnYear(yearLimitDto.getConsumptionTonOnYear())
                    .fuelType(yearLimitDto.getFuelType())
                    .build());
        });
    }
}
