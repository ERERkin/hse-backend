package kz.ccecc.hse_backend.service.batteryChargingService.impl;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingPollutionSourceDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingProductionDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingProduction;
import kz.ccecc.hse_backend.mapper.batteryChargingMapper.BatteryChargingPollutionSourceDtoMapper;
import kz.ccecc.hse_backend.mapper.batteryChargingMapper.BatteryChargingProductionDtoMapper;
import kz.ccecc.hse_backend.repository.batteryChargingRepository.BatteryChargingProductionRepository;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingPollutionSourceService;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingProductionService;
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
public class BatteryChargingProductionServiceImpl extends AbstractService<BatteryChargingProduction,
        BatteryChargingProductionDto,
        BatteryChargingProductionRepository,
        BatteryChargingProductionDtoMapper>
        implements BatteryChargingProductionService {
    public BatteryChargingProductionServiceImpl(BatteryChargingProductionRepository repository, BatteryChargingProductionDtoMapper mapper) {
        super(repository, mapper, "batteryChargingProduction");
    }

    @Autowired
    BatteryChargingPollutionSourceDtoMapper batteryChargingPollutionSourceDtoMapper;
    @Autowired
    BatteryChargingPollutionSourceService batteryChargingPollutionSourceService;

    @Override
    public BatteryChargingProductionDto save(BatteryChargingProductionDto item) {
        BatteryChargingProductionDto productionSaved = super.save(item);
        if(Objects.isNull(productionSaved.getPollutionSources())) return productionSaved;
        List<BatteryChargingPollutionSourceDto> pollutionSources = new ArrayList<>();
        item.getPollutionSources().forEach(pollutionSourceDto -> {
            pollutionSourceDto.setProduction(productionSaved);
            BatteryChargingPollutionSourceDto pollutionSource = batteryChargingPollutionSourceService.save(pollutionSourceDto);
            pollutionSource.setProduction(null);
            pollutionSources.add(pollutionSource);
        });

        productionSaved.setPollutionSources(pollutionSources);
        return productionSaved;
    }
}
