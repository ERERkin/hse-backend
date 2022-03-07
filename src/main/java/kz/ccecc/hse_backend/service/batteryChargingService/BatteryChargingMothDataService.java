package kz.ccecc.hse_backend.service.batteryChargingService;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingQuarterDataDto;
import kz.ccecc.hse_backend.service.base.BaseService;
import org.springframework.stereotype.Service;

public interface BatteryChargingMothDataService extends BaseService<BatteryChargingMothDataDto> {
    BatteryChargingQuarterDataDto getQuarterDateByYearAndQuarterNum(Long yearLimitId, Long year, Long num);
}
