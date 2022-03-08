package kz.ccecc.hse_backend.service.batteryChargingService.impl;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingPollutionSourceDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearLimitDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingPollutionSource;
import kz.ccecc.hse_backend.mapper.batteryChargingMapper.BatteryChargingPollutionSourceDtoMapper;
import kz.ccecc.hse_backend.mapper.batteryChargingMapper.BatteryChargingYearLimitDtoMapper;
import kz.ccecc.hse_backend.repository.batteryChargingRepository.BatteryChargingPollutionSourceRepository;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingPollutionSourceService;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingYearLimitService;
import kz.ccecc.hse_backend.service.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BatteryChargingPollutionSourceServiceImpl extends AbstractService<BatteryChargingPollutionSource,
        BatteryChargingPollutionSourceDto,
        BatteryChargingPollutionSourceRepository,
        BatteryChargingPollutionSourceDtoMapper>
        implements BatteryChargingPollutionSourceService {
    public BatteryChargingPollutionSourceServiceImpl(BatteryChargingPollutionSourceRepository repository, BatteryChargingPollutionSourceDtoMapper mapper) {
        super(repository, mapper, "batteryChargingPollutionSource");
    }

    @Autowired
    BatteryChargingYearLimitDtoMapper batteryChargingYearLimitDtoMapper;
    @Autowired
    BatteryChargingYearLimitService batteryChargingYearLimitService;

    @Override
    public BatteryChargingPollutionSourceDto save(BatteryChargingPollutionSourceDto item) {
        BatteryChargingPollutionSourceDto pollutionSourceSaved = super.save(item);
        if(Objects.isNull(item.getYearLimits())) return pollutionSourceSaved;
        List<BatteryChargingYearLimitDto> yearLimits = new ArrayList<>();
        item.getYearLimits().forEach(yearLimitDto -> {
            yearLimitDto.setPollutionSource(pollutionSourceSaved);
            BatteryChargingYearLimitDto yearLimitSaved = batteryChargingYearLimitService.save(yearLimitDto);
            yearLimitSaved.setPollutionSource(null);
            yearLimits.add(yearLimitSaved);
        });
        pollutionSourceSaved.setYearLimits(yearLimits);
        return pollutionSourceSaved;
    }
}
