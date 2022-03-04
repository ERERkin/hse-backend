package kz.ccecc.hse_backend.service.batteryChargingService.impl;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingMothData;
import kz.ccecc.hse_backend.mapper.batteryChargingMapper.BatteryChargingMothDataToBatteryChargingMothDataDtoMapper;
import kz.ccecc.hse_backend.repository.batteryChargingRepository.BatteryChargingMothDataRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingMothDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BatteryChargingMothDataServiceImpl extends AbstractService<BatteryChargingMothData,
        BatteryChargingMothDataDto,
        BatteryChargingMothDataRepository,
        BatteryChargingMothDataToBatteryChargingMothDataDtoMapper>
        implements BatteryChargingMothDataService {
    public BatteryChargingMothDataServiceImpl(BatteryChargingMothDataRepository repository,
                                              BatteryChargingMothDataToBatteryChargingMothDataDtoMapper mapper) {
        super(repository, mapper, "batteryChargingMothData");
    }
}
