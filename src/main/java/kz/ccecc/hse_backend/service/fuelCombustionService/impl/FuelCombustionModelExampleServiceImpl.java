package kz.ccecc.hse_backend.service.fuelCombustionService.impl;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.*;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionMothData;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionModelExampleService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FuelCombustionModelExampleServiceImpl implements FuelCombustionModelExampleService {
    @Override
    public FuelCombustionFuelTypeDto getFuelCombustionFuelTypeExampleV1() {
        List<FuelCombustionYearLimitDto> yearLimitDtoList = new ArrayList<>();
        yearLimitDtoList.add(getFuelCombustionYearLimitExampleV1());
        return FuelCombustionFuelTypeDto.builder()
                .fuelType("природный газ")
                .fuelDensity(BigDecimal.valueOf(0.73))
                .yearLimits(yearLimitDtoList)
                .build();
    }

    @Override
    public FuelCombustionFuelTypeDto getFuelCombustionFuelTypeExampleV2() {
        List<FuelCombustionYearLimitDto> yearLimitDtoList = new ArrayList<>();
        yearLimitDtoList.add(getFuelCombustionYearLimitExampleV2());
        return FuelCombustionFuelTypeDto.builder()
                .fuelType("дизтопливо")
                .fuelDensity(BigDecimal.valueOf(840))
                .yearLimits(yearLimitDtoList)
                .build();
    }

    @Override
    public FuelCombustionFuelTypeDto getFuelCombustionFuelTypeExampleV3() {
        List<FuelCombustionYearLimitDto> yearLimitDtoList = new ArrayList<>();
        yearLimitDtoList.add(getFuelCombustionYearLimitExampleV3());
        return FuelCombustionFuelTypeDto.builder()
                .fuelType("электрод: AWS A5.5 E7015 (аналог УОНИ 13/55)")
                .fuelDensity(null)
                .yearLimits(yearLimitDtoList)
                .build();
    }

    @Override
    public FuelCombustionMothDataDto getFuelCombustionMothDataExampleV1() {
        return FuelCombustionMothDataDto.builder()
                .month("2022-01")
                .workTime(BigDecimal.valueOf(500))
                .consumptionM3OnMonth(BigDecimal.valueOf(3400000))
                .consumptionLiterOnMonth(null)
                .consumptionKgOnMonth(null)
                .consumptionTonOnMonth(null)
                .build();
    }

    @Override
    public FuelCombustionMothDataDto getFuelCombustionMothDataExampleV2() {
        return FuelCombustionMothDataDto.builder()
                .month("2022-01")
                .workTime(BigDecimal.valueOf(500))
                .consumptionM3OnMonth(null)
                .consumptionLiterOnMonth(BigDecimal.valueOf(113.591))
                .consumptionKgOnMonth(null)
                .consumptionTonOnMonth(null)
                .build();
    }

    @Override
    public FuelCombustionMothDataDto getFuelCombustionMothDataExampleV3() {
        return FuelCombustionMothDataDto.builder()
                .month("2022-01")
                .workTime(BigDecimal.valueOf(500))
                .consumptionM3OnMonth(null)
                .consumptionLiterOnMonth(null)
                .consumptionKgOnMonth(BigDecimal.valueOf(60))
                .consumptionTonOnMonth(null)
                .build();
    }

    @Override
    public FuelCombustionPollutionSourceDto getFuelCombustionPollutionSourceExampleV1() {
        List<FuelCombustionFuelTypeDto> fuelTypeDtoList = new ArrayList<>();
        fuelTypeDtoList.add(getFuelCombustionFuelTypeExampleV1());
        return FuelCombustionPollutionSourceDto.builder()
                .name("Выхлопная труба ГПА №1 Роллс-Ройс, RB 211-DLE 24G")
                .number("0001")
                .fuelTypes(fuelTypeDtoList)
                .build();
    }

    @Override
    public FuelCombustionPollutionSourceDto getFuelCombustionPollutionSourceExampleV2() {
        List<FuelCombustionFuelTypeDto> fuelTypeDtoList = new ArrayList<>();
        fuelTypeDtoList.add(getFuelCombustionFuelTypeExampleV2());
        return FuelCombustionPollutionSourceDto.builder()
                .name("Выхлопная труба дизель-генератора  Caterpiller C15")
                .number("0097")
                .fuelTypes(fuelTypeDtoList)
                .build();
    }

    @Override
    public FuelCombustionPollutionSourceDto getFuelCombustionPollutionSourceExampleV3() {
        List<FuelCombustionFuelTypeDto> fuelTypeDtoList = new ArrayList<>();
        fuelTypeDtoList.add(getFuelCombustionFuelTypeExampleV3());
        return FuelCombustionPollutionSourceDto.builder()
                .name("Сварочные работы на территории КC")
                .number("6001")
                .fuelTypes(fuelTypeDtoList)
                .build();
    }

    @Override
    public FuelCombustionProductionDto getFuelCombustionProductionExampleV1() {
        List<FuelCombustionPollutionSourceDto> pollutionSourceDtoList = new ArrayList<>();
        pollutionSourceDtoList.add(getFuelCombustionPollutionSourceExampleV1());
        return FuelCombustionProductionDto.builder()
                .name("Компрессорный цех №1")
                .pollutionSources(pollutionSourceDtoList)
                .build();
    }

    @Override
    public FuelCombustionProductionDto getFuelCombustionProductionExampleV2() {
        List<FuelCombustionPollutionSourceDto> pollutionSourceDtoList = new ArrayList<>();
        pollutionSourceDtoList.add(getFuelCombustionPollutionSourceExampleV2());
        return FuelCombustionProductionDto.builder()
                .name("ПЭБ")
                .pollutionSources(pollutionSourceDtoList)
                .build();
    }

    @Override
    public FuelCombustionProductionDto getFuelCombustionProductionExampleV3() {
        List<FuelCombustionPollutionSourceDto> pollutionSourceDtoList = new ArrayList<>();
        pollutionSourceDtoList.add(getFuelCombustionPollutionSourceExampleV3());
        return FuelCombustionProductionDto.builder()
                .name("Компрессорный цех №1")
                .pollutionSources(pollutionSourceDtoList)
                .build();
    }

    @Override
    public FuelCombustionYearLimitDto getFuelCombustionYearLimitExampleV1() {
        List<FuelCombustionMothDataDto> mothDataList = new ArrayList<>();
        mothDataList.add(getFuelCombustionMothDataExampleV1());
        return FuelCombustionYearLimitDto.builder()
                .year(2022L)
                .workTime(BigDecimal.valueOf(6000))
                .consumptionM3OnYear(BigDecimal.valueOf(40800000))
                .consumptionKgOnYear(null)
                .consumptionTonOnYear(null)
                .mothDataList(mothDataList)
                .build();
    }

    @Override
    public FuelCombustionYearLimitDto getFuelCombustionYearLimitExampleV2() {
        List<FuelCombustionMothDataDto> mothDataList = new ArrayList<>();
        mothDataList.add(getFuelCombustionMothDataExampleV2());
        return FuelCombustionYearLimitDto.builder()
                .year(2022L)
                .workTime(BigDecimal.valueOf(16))
                .consumptionM3OnYear(null)
                .consumptionKgOnYear(null)
                .consumptionTonOnYear(BigDecimal.valueOf(1.145))
                .mothDataList(mothDataList)
                .build();
    }

    @Override
    public FuelCombustionYearLimitDto getFuelCombustionYearLimitExampleV3() {
        List<FuelCombustionMothDataDto> mothDataList = new ArrayList<>();
        mothDataList.add(getFuelCombustionMothDataExampleV3());
        return FuelCombustionYearLimitDto.builder()
                .year(2022L)
                .workTime(BigDecimal.valueOf(360))
                .consumptionM3OnYear(null)
                .consumptionKgOnYear(BigDecimal.valueOf(720))
                .consumptionTonOnYear(null)
                .mothDataList(mothDataList)
                .build();
    }
}
