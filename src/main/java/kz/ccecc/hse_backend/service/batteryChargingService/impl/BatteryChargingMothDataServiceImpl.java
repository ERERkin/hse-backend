package kz.ccecc.hse_backend.service.batteryChargingService.impl;

import kz.ccecc.hse_backend.dto.ObjectNotFoundException;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingQuarterDataDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingMothData;
import kz.ccecc.hse_backend.mapper.batteryChargingMapper.BatteryChargingMothDataDtoMapper;
import kz.ccecc.hse_backend.repository.batteryChargingRepository.BatteryChargingMothDataRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingMothDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BatteryChargingMothDataServiceImpl extends AbstractService<BatteryChargingMothData,
        BatteryChargingMothDataDto,
        BatteryChargingMothDataRepository,
        BatteryChargingMothDataDtoMapper>
        implements BatteryChargingMothDataService {
    public BatteryChargingMothDataServiceImpl(BatteryChargingMothDataRepository repository,
                                              BatteryChargingMothDataDtoMapper mapper) {
        super(repository, mapper, "batteryChargingMothData");
    }

    @Override
    public BatteryChargingQuarterDataDto getQuarterDateByYearAndQuarterNum(Long yearLimitId, Long year, Long num) {
        List<BatteryChargingMothDataDto> batteryChargingMothDataDtoList = mapper.toDtos(repository
                .getBatteryChargingMothDataByDateQuarter(yearLimitId, quarterStartDate(year, num), quarterEndDate(year, num)));

        if(batteryChargingMothDataDtoList.size() == 0) return null;

        return BatteryChargingQuarterDataDto.builder()
                .quarter(year + "-" + num)
                .batteryCount(batteryChargingMothDataDtoList.stream().map(BatteryChargingMothDataDto::getBatteryCount).reduce(0L, Long::sum))
                .batteryCapacity(batteryChargingMothDataDtoList.stream().map(BatteryChargingMothDataDto::getBatteryCapacity).reduce(0., Double::sum))
                .workTime(batteryChargingMothDataDtoList.stream().map(BatteryChargingMothDataDto::getWorkTime).reduce(0., Double::sum))
                .build();
    }

    private LocalDate quarterStartDate(Long year, Long num){
        switch (num.intValue()){
            case 1:
                return LocalDate.of(year.intValue(), 1, 1);
            case 2:
                return LocalDate.of(year.intValue(), 4, 1);
            case 3:
                return LocalDate.of(year.intValue(), 7, 1);
            case 4:
                return LocalDate.of(year.intValue(), 10, 1);
        }
        throw new ObjectNotFoundException("Wrong year or quarter");
    }

    private LocalDate quarterEndDate(Long year, Long num){
        switch (num.intValue()){
            case 1:
                return LocalDate.of(year.intValue(), 4, 1).minusDays(1);
            case 2:
                return LocalDate.of(year.intValue(), 7, 1).minusDays(1);
            case 3:
                return LocalDate.of(year.intValue(), 10, 1).minusDays(1);
            case 4:
                return LocalDate.of(year.intValue() + 1, 1, 1).minusDays(1);
        }
        throw new ObjectNotFoundException("Wrong year or quarter");
    }
}
