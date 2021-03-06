package kz.ccecc.hse_backend.service.batteryChargingService.impl;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingQuarterDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearLimitDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingYearLimit;
import kz.ccecc.hse_backend.mapper.batteryChargingMapper.BatteryChargingYearLimitDtoMapper;
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
public class BatteryChargingYearLimitServiceImpl extends AbstractService<BatteryChargingYearLimit, BatteryChargingYearLimitDto, BatteryChargingYearLimitRepository, BatteryChargingYearLimitDtoMapper>
        implements BatteryChargingYearLimitService {
    public BatteryChargingYearLimitServiceImpl(BatteryChargingYearLimitRepository repository, BatteryChargingYearLimitDtoMapper mapper) {
        super(repository, mapper, "batteryChargingYearLimit");
    }

    @Autowired
    BatteryChargingMothDataService batteryChargingMothDataService;

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
        return batteryChargingYearLimitSaved;
    }
}
