package kz.ccecc.hse_backend.sheduler;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearLimitDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingYearLimit;
import kz.ccecc.hse_backend.mapper.batteryChargingMapper.BatteryChargingYearLimitDtoMapper;
import kz.ccecc.hse_backend.repository.batteryChargingRepository.BatteryChargingYearLimitRepository;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingYearLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BatteryChargingSheduler {
    @Autowired
    BatteryChargingYearLimitService batteryChargingYearLimitService;
    @Autowired
    BatteryChargingYearLimitRepository batteryChargingYearLimitRepository;
    @Autowired
    BatteryChargingYearLimitDtoMapper batteryChargingYearLimitDtoMapper;

//    @Scheduled(cron="0 0 0 25 12 ?") // it will run 25th December every year
    @Scheduled(cron = "0 0 0 1 1 ?") // it will run 1st January every year
    void createYearLimit(){
        List<BatteryChargingYearLimit> yearLimitList = batteryChargingYearLimitRepository
                        .getBatteryChargingYearLimitByYear(Long.parseLong("" + LocalDate.now().getYear()));

        List<BatteryChargingYearLimitDto> yearLimitDtoList = batteryChargingYearLimitDtoMapper.toDtos(yearLimitList);

        yearLimitDtoList.forEach(yearLimitDto -> {
            batteryChargingYearLimitService.save(BatteryChargingYearLimitDto.builder()
                            .year(yearLimitDto.getYear() + 1)
                            .workTime(yearLimitDto.getWorkTime())
                            .batteryModel(yearLimitDto.getBatteryModel())
                            .batteryCount(yearLimitDto.getBatteryCount())
                            .batteryCapacity(yearLimitDto.getBatteryCapacity())
                            .pollutionSource(yearLimitDto.getPollutionSource())
                    .build());
        });
    }
}
