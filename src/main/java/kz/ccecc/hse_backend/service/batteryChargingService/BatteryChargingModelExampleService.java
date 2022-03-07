package kz.ccecc.hse_backend.service.batteryChargingService;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingPollutionSourceDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingProductionDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearLimitDto;

public interface BatteryChargingModelExampleService {
    BatteryChargingMothDataDto getBatteryChargingMothDataExampleV1();

    BatteryChargingPollutionSourceDto getBatteryChargingPollutionSourceExampleV1();

    BatteryChargingProductionDto getBatteryChargingProductionExampleV1();

    BatteryChargingYearLimitDto getBatteryChargingYearLimitExampleV1();
}
