package kz.ccecc.hse_backend.service.technicalEquipmentSPRService.impl;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.*;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRModelExampleService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TechnicalEquipmentSPRModelExampleServiceImpl implements TechnicalEquipmentSPRModelExampleService {
    @Override
    public TechnicalEquipmentSPRProductionDto getTechnicalEquipmentSPRProductionExampleV1() {
        List<TechnicalEquipmentSPRPollutionSourceDto> pollutionSourceDtoList = new ArrayList<>();
        pollutionSourceDtoList.add(getTechnicalEquipmentSPRPollutionSourceExampleV1());
        return TechnicalEquipmentSPRProductionDto.builder()
                .name("КС-КЦ №1")
                .pollutionSources(pollutionSourceDtoList)
                .build();
    }

    @Override
    public TechnicalEquipmentSPRProductionDto getTechnicalEquipmentSPRProductionExampleV2() {
        List<TechnicalEquipmentSPRPollutionSourceDto> pollutionSourceDtoList = new ArrayList<>();
        pollutionSourceDtoList.add(getTechnicalEquipmentSPRPollutionSourceExampleV2());
        return TechnicalEquipmentSPRProductionDto.builder()
                .name("КС-КЦ №1")
                .pollutionSources(pollutionSourceDtoList)
                .build();
    }

    @Override
    public TechnicalEquipmentSPRPollutionSourceDto getTechnicalEquipmentSPRPollutionSourceExampleV1() {
        List<TechnicalEquipmentSPRFuelTypeDto> fuelTypeDtoList = new ArrayList<>();
        fuelTypeDtoList.add(getTechnicalEquipmentSPRFuelTypeExampleV1());
        return TechnicalEquipmentSPRPollutionSourceDto.builder()
                .name("Свеча №1 первичного сброса газа с системы сухого газового уплотнения не приводной стороны центробежного нагнетателя ГПА №1")
                .number("0019")
                .fuelTypes(fuelTypeDtoList)
                .build();
    }

    @Override
    public TechnicalEquipmentSPRPollutionSourceDto getTechnicalEquipmentSPRPollutionSourceExampleV2() {
        List<TechnicalEquipmentSPRFuelTypeDto> fuelTypeDtoList = new ArrayList<>();
        fuelTypeDtoList.add(getTechnicalEquipmentSPRFuelTypeExampleV2());
        return TechnicalEquipmentSPRPollutionSourceDto.builder()
                .name("Свеча №4 контура системы топливного газа электромагнитным клапаном 20FGESI и клапаном 20 FGI (внутри кожуха). Перед каждым запуском и после останова ГПА №1")
                .number("0031")
                .fuelTypes(fuelTypeDtoList)
                .build();
    }

    @Override
    public TechnicalEquipmentSPRFuelTypeDto getTechnicalEquipmentSPRFuelTypeExampleV1() {
        List<TechnicalEquipmentSPRYearLimitDto> yearLimitDtoList = new ArrayList<>();
        yearLimitDtoList.add(getTechnicalEquipmentSPRYearLimitExampleV1());
        return TechnicalEquipmentSPRFuelTypeDto.builder()
                .fuelType("природный газ ")
                .fuelDensity(BigDecimal.valueOf(0.73))
                .yearLimits(yearLimitDtoList)
                .build();
    }

    @Override
    public TechnicalEquipmentSPRFuelTypeDto getTechnicalEquipmentSPRFuelTypeExampleV2() {
        List<TechnicalEquipmentSPRYearLimitDto> yearLimitDtoList = new ArrayList<>();
        yearLimitDtoList.add(getTechnicalEquipmentSPRYearLimitExampleV2());
        return TechnicalEquipmentSPRFuelTypeDto.builder()
                .fuelType("природный газ ")
                .fuelDensity(BigDecimal.valueOf(0.73))
                .yearLimits(yearLimitDtoList)
                .build();
    }

    @Override
    public TechnicalEquipmentSPRYearLimitDto getTechnicalEquipmentSPRYearLimitExampleV1() {
        List<TechnicalEquipmentSPRMothDataDto> mothDataDtoList = new ArrayList<>();
        mothDataDtoList.add(getTechnicalEquipmentSPRMothDataExampleV1());
        return TechnicalEquipmentSPRYearLimitDto.builder()
                .year(2022L)
                .count("постоянно при работе ГПА")
                .volume(BigDecimal.valueOf(2370))
                .mothDataList(mothDataDtoList)
                .build();
    }

    @Override
    public TechnicalEquipmentSPRYearLimitDto getTechnicalEquipmentSPRYearLimitExampleV2() {
        List<TechnicalEquipmentSPRMothDataDto> mothDataDtoList = new ArrayList<>();
        mothDataDtoList.add(getTechnicalEquipmentSPRMothDataExampleV2());
        return TechnicalEquipmentSPRYearLimitDto.builder()
                .year(2022L)
                .count("65")
                .volume(BigDecimal.valueOf(195))
                .mothDataList(mothDataDtoList)
                .build();
    }

    @Override
    public TechnicalEquipmentSPRMothDataDto getTechnicalEquipmentSPRMothDataExampleV1() {
        return TechnicalEquipmentSPRMothDataDto.builder()
                .month("2022-01")
                .count("постоянно при работе ГПА")
                .volume(BigDecimal.valueOf(197.5))
                .build();
    }

    @Override
    public TechnicalEquipmentSPRMothDataDto getTechnicalEquipmentSPRMothDataExampleV2() {
        return TechnicalEquipmentSPRMothDataDto.builder()
                .month("2022-01")
                .count("65")
                .volume(BigDecimal.valueOf(16.25))
                .build();
    }
}
