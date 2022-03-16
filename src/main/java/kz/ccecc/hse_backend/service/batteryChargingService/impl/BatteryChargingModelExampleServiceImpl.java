package kz.ccecc.hse_backend.service.batteryChargingService.impl;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingPollutionSourceDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingProductionDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearLimitDto;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingModelExampleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BatteryChargingModelExampleServiceImpl implements BatteryChargingModelExampleService {
    @Override
    public BatteryChargingMothDataDto getBatteryChargingMothDataExampleV1() {
        return BatteryChargingMothDataDto.builder()
                .month("2022-01")
                .workTime(730.)
                .batteryCount(32L)
                .batteryCapacity(120.)
                .build();
    }

    @Override
    public BatteryChargingPollutionSourceDto getBatteryChargingPollutionSourceExampleV1() {
        List<BatteryChargingYearLimitDto> batteryChargingYearLimitDtoList = new ArrayList<>();
        batteryChargingYearLimitDtoList.add(getBatteryChargingYearLimitExampleV1());
        return BatteryChargingPollutionSourceDto.builder()
                .name("Аккумуляторная АБК КС")
                .number("0106")
                .production(null)
                .yearLimits(batteryChargingYearLimitDtoList)
                .build();
    }

    @Override
    public BatteryChargingProductionDto getBatteryChargingProductionExampleV1() {
        List<BatteryChargingPollutionSourceDto> batteryChargingPollutionSourceDtoList = new ArrayList<>();
        batteryChargingPollutionSourceDtoList.add(getBatteryChargingPollutionSourceExampleV1());
        return BatteryChargingProductionDto.builder()
                .name("КС. Административно-бытовой корпус")
                .pollutionSources(batteryChargingPollutionSourceDtoList)
                .build();
    }

    @Override
    public BatteryChargingYearLimitDto getBatteryChargingYearLimitExampleV1() {
        List<BatteryChargingMothDataDto> batteryChargingMothDataDtoList = new ArrayList<>();
        batteryChargingMothDataDtoList.add(getBatteryChargingMothDataExampleV1());
        return BatteryChargingYearLimitDto.builder()
                .year(2022L)
                .batteryModel("2V 120-12")
                .workTime(730.)
                .batteryCount(32L)
                .batteryCapacity(120.)
                .mothDataList(batteryChargingMothDataDtoList)
                .build();
    }
}
