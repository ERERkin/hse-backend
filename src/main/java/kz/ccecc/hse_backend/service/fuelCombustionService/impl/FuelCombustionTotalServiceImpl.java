package kz.ccecc.hse_backend.service.fuelCombustionService.impl;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.*;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionYearLimit;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionMothDataDtoMapper;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionYearLimitDtoMapper;
import kz.ccecc.hse_backend.repository.fuelCombustionRepository.FuelCombustionYearLimitRepository;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FuelCombustionTotalServiceImpl implements FuelCombustionTotalService {
    private final DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("yyyy-MM");

    @Autowired
    FuelCombustionYearLimitRepository fuelCombustionYearLimitRepository;
    @Autowired
    FuelCombustionYearLimitDtoMapper fuelCombustionYearLimitDtoMapper;

    @Override
    public List<FuelCombustionTotalDataResponse> getFuelCombustionTotal(Long year) {
        List<FuelCombustionYearLimit> yearLimitList = fuelCombustionYearLimitRepository.getFuelCombustionYearLimitByYear(year);
        List<FuelCombustionYearLimitDto> yearLimitDtoList = fuelCombustionYearLimitDtoMapper.toDtos(yearLimitList);
        HashMap<String, FuelCombustionTotalDataDto> totalDataDtoHashMap = new HashMap<>();
        List<FuelCombustionTotalDataDto> totalDataDtoList = new ArrayList<>();
        yearLimitDtoList.forEach(yearLimit -> {
            if (!totalDataDtoHashMap.containsKey(yearLimit.getFuelType().getFuelCategory())) {
                totalDataDtoHashMap.put(yearLimit.getFuelType().getFuelCategory(), createTotalDataDto(yearLimit));
            } else {
                totalDataDtoHashMap.put(yearLimit.getFuelType().getFuelCategory(),
                        addTotalData(totalDataDtoHashMap.get(yearLimit.getFuelType().getFuelCategory()), createTotalDataDto(yearLimit)));
            }
        });
        for (Map.Entry<String, FuelCombustionTotalDataDto> totalDataDto : totalDataDtoHashMap.entrySet()) {
            totalDataDtoList.add(totalDataDto.getValue());
        }
        return totalDataDtoList.stream().map(totalDataDto -> FuelCombustionTotalDataResponse.builder()
                    .category(totalDataDto.getCategory())
                    .mothDataDtoList(convertMothDataToMothResponse(totalDataDto.getMothDataDtoHashMap()))
                    .quarterDataDtoList(convertQuarterDataToQuarterResponse(totalDataDto.getQuarterDataDtoHashMap()))
                    .yearData(totalDataDto.getYearData())
                    .yearLimit(totalDataDto.getYearLimit())
                    .build())
                .collect(Collectors.toList());
    }

    List<FuelCombustionMothDataDto> convertMothDataToMothResponse(HashMap<String, FuelCombustionMothDataDto> mothDataDtoHashMap) {
        List<FuelCombustionMothDataDto> mothDataDtoList = new ArrayList<>();
        for (Map.Entry<String, FuelCombustionMothDataDto> mothDataDto : mothDataDtoHashMap.entrySet()) {
            mothDataDtoList.add(mothDataDto.getValue());
        }
        return mothDataDtoList;
    }

    List<FuelCombustionQuarterDataDto> convertQuarterDataToQuarterResponse(HashMap<String, FuelCombustionQuarterDataDto> quarterDataDtoHashMap) {
        List<FuelCombustionQuarterDataDto> quarterDataDtoList = new ArrayList<>();
        for (Map.Entry<String, FuelCombustionQuarterDataDto> quarterData : quarterDataDtoHashMap.entrySet()) {
            quarterDataDtoList.add(quarterData.getValue());
        }
        return quarterDataDtoList;
    }

    private FuelCombustionMothDataDto addMonthData(FuelCombustionMothDataDto mothDataDtoInMap, FuelCombustionMothDataDto mothDataDto) {
        if (Objects.isNull(mothDataDto)) return mothDataDtoInMap;
        return FuelCombustionMothDataDto.builder()
                .month(mothDataDtoInMap.getMonth())
                .workTime(addBigDemical(mothDataDtoInMap.getWorkTime(), mothDataDto.getWorkTime()))
                .consumptionM3OnMonth(addBigDemical(mothDataDtoInMap.getConsumptionM3OnMonth(), mothDataDto.getConsumptionKgOnMonth()))
                .consumptionKgOnMonth(addBigDemical(mothDataDtoInMap.getConsumptionKgOnMonth(), mothDataDto.getConsumptionKgOnMonth()))
                .consumptionLiterOnMonth(addBigDemical(mothDataDtoInMap.getConsumptionLiterOnMonth(), mothDataDto.getConsumptionLiterOnMonth()))
                .consumptionTonOnMonth(addBigDemical(mothDataDtoInMap.getConsumptionTonOnMonth(), mothDataDto.getConsumptionTonOnMonth()))
                .build();
    }

    private FuelCombustionQuarterDataDto addQuarterData(FuelCombustionQuarterDataDto quarterDataDtoInMap, FuelCombustionQuarterDataDto quarterDataDto) {
        if (Objects.isNull(quarterDataDto)) return quarterDataDtoInMap;
        return FuelCombustionQuarterDataDto.builder()
                .quarter(quarterDataDtoInMap.getQuarter())
                .workTime(addBigDemical(quarterDataDtoInMap.getWorkTime(), quarterDataDto.getWorkTime()))
                .consumptionM3OnMonth(addBigDemical(quarterDataDtoInMap.getConsumptionM3OnMonth(), quarterDataDto.getConsumptionM3OnMonth()))
                .consumptionKgOnMonth(addBigDemical(quarterDataDtoInMap.getConsumptionKgOnMonth(), quarterDataDto.getConsumptionKgOnMonth()))
                .consumptionLiterOnMonth(addBigDemical(quarterDataDtoInMap.getConsumptionLiterOnMonth(), quarterDataDto.getConsumptionLiterOnMonth()))
                .consumptionTonOnMonth(addBigDemical(quarterDataDtoInMap.getConsumptionTonOnMonth(), quarterDataDto.getConsumptionTonOnMonth()))
                .consumptionM3OnMonthLimit(addBigDemical(quarterDataDtoInMap.getConsumptionM3OnMonthLimit(), quarterDataDto.getConsumptionM3OnMonthLimit()))
                .consumptionKgOnMonthLimit(addBigDemical(quarterDataDtoInMap.getConsumptionKgOnMonthLimit(), quarterDataDto.getConsumptionKgOnMonthLimit()))
                .consumptionTonOnMonthLimit(addBigDemical(quarterDataDtoInMap.getConsumptionTonOnMonthLimit(), quarterDataDto.getConsumptionTonOnMonthLimit()))
                .build();
    }

    private FuelCombustionYearDataDto addYearData(FuelCombustionYearDataDto yearDataDtoInMap, FuelCombustionYearDataDto yearDataDto) {
        if (Objects.isNull(yearDataDto)) return yearDataDtoInMap;
        return FuelCombustionYearDataDto.builder()
                .year(yearDataDtoInMap.getYear())
                .workTime(addBigDemical(yearDataDtoInMap.getWorkTime(), yearDataDto.getWorkTime()))
                .consumptionM3OnMonth(addBigDemical(yearDataDtoInMap.getConsumptionM3OnMonth(), yearDataDto.getConsumptionKgOnMonth()))
                .consumptionKgOnMonth(addBigDemical(yearDataDtoInMap.getConsumptionKgOnMonth(), yearDataDto.getConsumptionKgOnMonth()))
                .consumptionLiterOnMonth(addBigDemical(yearDataDtoInMap.getConsumptionLiterOnMonth(), yearDataDto.getConsumptionLiterOnMonth()))
                .consumptionTonOnMonth(addBigDemical(yearDataDtoInMap.getConsumptionTonOnMonth(), yearDataDto.getConsumptionTonOnMonth()))
                .build();
    }

    private FuelCombustionYearLimitDto addYearLimit(FuelCombustionYearLimitDto yearLimitDtoInMap, FuelCombustionYearLimitDto yearLimitDto) {
        if (Objects.isNull(yearLimitDto)) return yearLimitDtoInMap;
        return FuelCombustionYearLimitDto.builder()
                .year(yearLimitDtoInMap.getYear())
                .workTime(addBigDemical(yearLimitDtoInMap.getWorkTime(), yearLimitDto.getWorkTime()))
                .consumptionM3OnYear(addBigDemical(yearLimitDtoInMap.getConsumptionM3OnYear(), yearLimitDto.getConsumptionM3OnYear()))
                .consumptionKgOnYear(addBigDemical(yearLimitDtoInMap.getConsumptionKgOnYear(), yearLimitDto.getConsumptionKgOnYear()))
                .consumptionTonOnYear(addBigDemical(yearLimitDtoInMap.getConsumptionTonOnYear(), yearLimitDto.getConsumptionTonOnYear()))
                .build();
    }

    private HashMap<String, FuelCombustionQuarterDataDto> addQuarterDataDtoHashMaps(
            HashMap<String, FuelCombustionQuarterDataDto> dataDtoHashMapInMap,
            HashMap<String, FuelCombustionQuarterDataDto> dataDtoHashMap) {
        for (Map.Entry<String, FuelCombustionQuarterDataDto> quarterDataDto : dataDtoHashMapInMap.entrySet()) {
            dataDtoHashMap.put(quarterDataDto.getKey(), addQuarterData(quarterDataDto.getValue(), dataDtoHashMap.get(quarterDataDto.getKey())));
        }
        return dataDtoHashMap;
    }

    private HashMap<String, FuelCombustionMothDataDto> addMonthDataDtoHashMaps(
            HashMap<String, FuelCombustionMothDataDto> dataDtoHashMapInMap,
            HashMap<String, FuelCombustionMothDataDto> dataDtoHashMap) {
        for (Map.Entry<String, FuelCombustionMothDataDto> mothDataDto : dataDtoHashMapInMap.entrySet()) {
            dataDtoHashMap.put(mothDataDto.getKey(), addMonthData(mothDataDto.getValue(), dataDtoHashMap.get(mothDataDto.getKey())));
        }
        return dataDtoHashMap;
    }

    private FuelCombustionTotalDataDto addTotalData(FuelCombustionTotalDataDto totalDataDtoInMap, FuelCombustionTotalDataDto totalDataDto) {
        if (Objects.isNull(totalDataDto)) return totalDataDtoInMap;
        return FuelCombustionTotalDataDto.builder()
                .category(totalDataDtoInMap.getCategory())
                .yearData(totalDataDtoInMap.getYearData())
                .mothDataDtoHashMap(addMonthDataDtoHashMaps(totalDataDtoInMap.getMothDataDtoHashMap(), totalDataDto.getMothDataDtoHashMap()))
                .quarterDataDtoHashMap(addQuarterDataDtoHashMaps(totalDataDtoInMap.getQuarterDataDtoHashMap(), totalDataDto.getQuarterDataDtoHashMap()))
                .yearData(addYearData(totalDataDtoInMap.getYearData(), totalDataDto.getYearData()))
                .yearLimit(addYearLimit(totalDataDtoInMap.getYearLimit(), totalDataDto.getYearLimit()))
                .build();
    }

    private FuelCombustionTotalDataDto createTotalDataDto(FuelCombustionYearLimitDto yearLimit) {
        HashMap<String, FuelCombustionMothDataDto> mothDataDtoHashMap = new HashMap<>();
        HashMap<String, FuelCombustionQuarterDataDto> quarterDataDtoHashMap = new HashMap<>();
        yearLimit.getMothDataList().forEach(mothData -> {
            String month = mothData.getMonth();
            if (mothDataDtoHashMap.containsKey(month)) {
                mothDataDtoHashMap.put(month, addMonthData(mothDataDtoHashMap.get(month), mothData));
            } else {
                mothData.setYearLimit(null);
                mothDataDtoHashMap.put(month, mothData);
            }
        });
        yearLimit.getQuarterDataList().forEach(quarterDataDto -> {
            String quarter = quarterDataDto.getQuarter();
            if (quarterDataDtoHashMap.containsKey(quarter)) {
                quarterDataDtoHashMap.put(quarter, addQuarterData(quarterDataDtoHashMap.get(quarter), quarterDataDto));
            } else {
                quarterDataDto.setYearLimit(null);
                quarterDataDtoHashMap.put(quarter, quarterDataDto);
            }
        });
        FuelCombustionYearDataDto yearData = yearLimit.getYearData();
        yearData.setYearLimit(null);
        return FuelCombustionTotalDataDto.builder()
                .category(yearLimit.getFuelType().getFuelCategory())
                .mothDataDtoHashMap(mothDataDtoHashMap)
                .quarterDataDtoHashMap(quarterDataDtoHashMap)
                .yearData(yearData)
                .yearLimit(yearLimit)
                .build();
    }

    private BigDecimal addBigDemical(BigDecimal one, BigDecimal two) {
        if(Objects.isNull(one)) one = BigDecimal.ZERO;
        if(Objects.isNull(two)) two = BigDecimal.ZERO;
        return one.add(two);
    }
}
