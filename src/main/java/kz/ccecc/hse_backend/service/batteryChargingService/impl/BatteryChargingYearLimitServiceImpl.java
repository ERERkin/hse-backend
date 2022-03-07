package kz.ccecc.hse_backend.service.batteryChargingService.impl;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingQuarterDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearLimitDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingYearLimit;
import kz.ccecc.hse_backend.mapper.batteryChargingMapper.BatteryChargingYearLimitToBatteryChargingYearLimitDtoMapper;
import kz.ccecc.hse_backend.repository.batteryChargingRepository.BatteryChargingYearLimitRepository;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingMothDataService;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingYearLimitService;
import kz.ccecc.hse_backend.service.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BatteryChargingYearLimitServiceImpl extends AbstractService<BatteryChargingYearLimit, BatteryChargingYearLimitDto, BatteryChargingYearLimitRepository, BatteryChargingYearLimitToBatteryChargingYearLimitDtoMapper>
        implements BatteryChargingYearLimitService {
    public BatteryChargingYearLimitServiceImpl(BatteryChargingYearLimitRepository repository, BatteryChargingYearLimitToBatteryChargingYearLimitDtoMapper mapper) {
        super(repository, mapper, "batteryChargingYearLimit");
    }

    @Autowired
    BatteryChargingMothDataService batteryChargingMothDataService;

    @Override
    public BatteryChargingYearLimitDto getById(Long id) {
        BatteryChargingYearLimitDto batteryChargingYearLimitDto = super.getById(id);
        if(Objects.nonNull(batteryChargingYearLimitDto.getId()) &&
                Objects.nonNull(batteryChargingYearLimitDto.getYear())){
            List<BatteryChargingQuarterDataDto> batteryChargingQuarterDataDtoList = new ArrayList<>();
            for(int i = 1; i <= 4; i++){
                BatteryChargingQuarterDataDto batteryChargingQuarterDataDto = batteryChargingMothDataService.getQuarterDateByYearAndQuarterNum(
                        batteryChargingYearLimitDto.getId(),
                        batteryChargingYearLimitDto.getYear(),
                        (long) i);
                if(Objects.nonNull(batteryChargingQuarterDataDto))
                    batteryChargingQuarterDataDtoList.add(batteryChargingQuarterDataDto);
            }
            batteryChargingYearLimitDto.setQuarterDataList(batteryChargingQuarterDataDtoList);
        }
        return batteryChargingYearLimitDto;
    }

    @Override
    public BatteryChargingYearLimitDto save(BatteryChargingYearLimitDto item) {
        BatteryChargingYearLimitDto batteryChargingYearLimitSaved = super.save(item);
        if(Objects.isNull(item.getMothDataList())) return batteryChargingYearLimitSaved;
        List<BatteryChargingMothDataDto> batteryChargingYearLimitDtoList = new ArrayList<>();
        item.getMothDataList().forEach(batteryChargingMothDataDto -> {
            batteryChargingMothDataDto.setYearLimit(batteryChargingYearLimitSaved);
            BatteryChargingMothDataDto batteryChargingMothDataSaved = batteryChargingMothDataService.save(batteryChargingMothDataDto);
            batteryChargingMothDataSaved.setYearLimit(null);
            batteryChargingYearLimitDtoList.add(batteryChargingMothDataSaved);
        });
        batteryChargingYearLimitSaved.setMothDataList(batteryChargingYearLimitDtoList);
        if(Objects.nonNull(batteryChargingYearLimitSaved.getId()) &&
                Objects.nonNull(batteryChargingYearLimitSaved.getYear())){
            List<BatteryChargingQuarterDataDto> batteryChargingQuarterDataDtoList = new ArrayList<>();
            for(int i = 1; i <= 4; i++){
                BatteryChargingQuarterDataDto batteryChargingQuarterDataDto = batteryChargingMothDataService.getQuarterDateByYearAndQuarterNum(
                        batteryChargingYearLimitSaved.getId(),
                        batteryChargingYearLimitSaved.getYear(),
                        (long) i);
                if(Objects.nonNull(batteryChargingQuarterDataDto))
                    batteryChargingQuarterDataDtoList.add(batteryChargingQuarterDataDto);
            }
            batteryChargingYearLimitSaved.setQuarterDataList(batteryChargingQuarterDataDtoList);
        }
        return batteryChargingYearLimitSaved;
    }

    @Override
    public List<BatteryChargingYearLimitDto> getAll() {
        List<BatteryChargingYearLimitDto> batteryChargingYearLimitDtoList = super.getAll();
        batteryChargingYearLimitDtoList = batteryChargingYearLimitDtoList.stream()
                .filter(batteryChargingYearLimitDto -> (Objects.nonNull(batteryChargingYearLimitDto.getId()) &&
                        Objects.nonNull(batteryChargingYearLimitDto.getYear())))
                .peek(batteryChargingYearLimitDto -> {
                    List<BatteryChargingQuarterDataDto> batteryChargingQuarterDataDtoList = new ArrayList<>();
                    for(int i = 1; i <= 4; i++){
                        BatteryChargingQuarterDataDto batteryChargingQuarterDataDto = batteryChargingMothDataService.getQuarterDateByYearAndQuarterNum(
                                batteryChargingYearLimitDto.getId(),
                                batteryChargingYearLimitDto.getYear(),
                                (long) i);
                        if(Objects.nonNull(batteryChargingQuarterDataDto))
                            batteryChargingQuarterDataDtoList.add(batteryChargingQuarterDataDto);
                    }
                    batteryChargingYearLimitDto.setQuarterDataList(batteryChargingQuarterDataDtoList);
                }).collect(Collectors.toList());
        return batteryChargingYearLimitDtoList;
    }
}
