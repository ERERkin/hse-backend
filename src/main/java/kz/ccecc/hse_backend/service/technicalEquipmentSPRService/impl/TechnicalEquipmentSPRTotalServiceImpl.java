package kz.ccecc.hse_backend.service.technicalEquipmentSPRService.impl;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.*;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRYearLimit;
import kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper.TechnicalEquipmentSPRYearLimitDtoMapper;
import kz.ccecc.hse_backend.repository.technicalEquipmentSPRRepository.TechnicalEquipmentSPRYearLimitRepository;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TechnicalEquipmentSPRTotalServiceImpl implements TechnicalEquipmentSPRTotalService {
    @Autowired
    TechnicalEquipmentSPRYearLimitRepository technicalEquipmentSPRYearLimitRepository;
    @Autowired
    TechnicalEquipmentSPRYearLimitDtoMapper technicalEquipmentSPRYearLimitDtoMapper;

    @Override
    public TechnicalEquipmentSPRTotalDataResponse getTechnicalEquipmentSPRTotal(Long year) {
        List<TechnicalEquipmentSPRYearLimit> yearLimitList = technicalEquipmentSPRYearLimitRepository.getTechnicalEquipmentSPRYearLimitByYear(year);
        List<TechnicalEquipmentSPRYearLimitDto> yearLimitDtoList = technicalEquipmentSPRYearLimitDtoMapper.toDtos(yearLimitList);
        TechnicalEquipmentSPRTotalDataDto totalDataDto = null;

        for(TechnicalEquipmentSPRYearLimitDto yearLimitDto : yearLimitDtoList){
            if (Objects.isNull(totalDataDto)) totalDataDto = createTotalDataDto(yearLimitDto);
            else totalDataDto = addTotalData(totalDataDto, createTotalDataDto(yearLimitDto));
        }
        assert totalDataDto != null;
        return TechnicalEquipmentSPRTotalDataResponse.builder()
                .yearData(totalDataDto.getYearData())
                .yearLimit(totalDataDto.getYearLimit())
                .mothDataList(convertMothDataToMothResponse(totalDataDto.getMothDataDtoHashMap()))
                .quarterDataList(convertQuarterDataToQuarterResponse(totalDataDto.getQuarterDataDtoHashMap()))
                .build();
    }

    List<TechnicalEquipmentSPRMothDataDto> convertMothDataToMothResponse(HashMap<String, TechnicalEquipmentSPRMothDataDto> mothDataDtoHashMap) {
        List<TechnicalEquipmentSPRMothDataDto> mothDataDtoList = new ArrayList<>();
        for (Map.Entry<String, TechnicalEquipmentSPRMothDataDto> mothDataDto : mothDataDtoHashMap.entrySet()) {
            mothDataDtoList.add(mothDataDto.getValue());
        }
        return mothDataDtoList;
    }

    List<TechnicalEquipmentSPRQuarterDataDto> convertQuarterDataToQuarterResponse(HashMap<String, TechnicalEquipmentSPRQuarterDataDto> quarterDataDtoHashMap) {
        List<TechnicalEquipmentSPRQuarterDataDto> quarterDataDtoList = new ArrayList<>();
        for (Map.Entry<String, TechnicalEquipmentSPRQuarterDataDto> quarterData : quarterDataDtoHashMap.entrySet()) {
            quarterDataDtoList.add(quarterData.getValue());
        }
        return quarterDataDtoList;
    }

    private TechnicalEquipmentSPRMothDataDto addMonthData(TechnicalEquipmentSPRMothDataDto mothDataDtoInMap, TechnicalEquipmentSPRMothDataDto mothDataDto) {
        if (Objects.isNull(mothDataDto)) return mothDataDtoInMap;
        return TechnicalEquipmentSPRMothDataDto.builder()
                .month(mothDataDtoInMap.getMonth())
                .volume(addBigDemical(mothDataDtoInMap.getVolume(), mothDataDto.getVolume()))
                .build();
    }

    private TechnicalEquipmentSPRQuarterDataDto addQuarterData(TechnicalEquipmentSPRQuarterDataDto quarterDataDtoInMap, TechnicalEquipmentSPRQuarterDataDto quarterDataDto) {
        if (Objects.isNull(quarterDataDto)) return quarterDataDtoInMap;
        return TechnicalEquipmentSPRQuarterDataDto.builder()
                .quarter(quarterDataDtoInMap.getQuarter())
                .volume(addBigDemical(quarterDataDtoInMap.getVolume(), quarterDataDto.getVolume()))
                .volumeLimit(addBigDemical(quarterDataDtoInMap.getVolumeLimit(), quarterDataDto.getVolumeLimit()))
                .build();
    }

    private TechnicalEquipmentSPRYearDataDto addYearData(TechnicalEquipmentSPRYearDataDto yearDataDtoInMap, TechnicalEquipmentSPRYearDataDto yearDataDto) {
        if (Objects.isNull(yearDataDto)) return yearDataDtoInMap;
        return TechnicalEquipmentSPRYearDataDto.builder()
                .year(yearDataDtoInMap.getYear())
                .volume(addBigDemical(yearDataDtoInMap.getVolume(), yearDataDto.getVolume()))
                .build();
    }

    private TechnicalEquipmentSPRYearLimitDto addYearLimit(TechnicalEquipmentSPRYearLimitDto yearLimitDtoInMap, TechnicalEquipmentSPRYearLimitDto yearLimitDto) {
        if (Objects.isNull(yearLimitDto)) return yearLimitDtoInMap;
        return TechnicalEquipmentSPRYearLimitDto.builder()
                .year(yearLimitDtoInMap.getYear())
                .volume(addBigDemical(yearLimitDtoInMap.getVolume(), yearLimitDto.getVolume()))
                .build();
    }

    private HashMap<String, TechnicalEquipmentSPRQuarterDataDto> addQuarterDataDtoHashMaps(
            HashMap<String, TechnicalEquipmentSPRQuarterDataDto> dataDtoHashMapInMap,
            HashMap<String, TechnicalEquipmentSPRQuarterDataDto> dataDtoHashMap) {
        for (Map.Entry<String, TechnicalEquipmentSPRQuarterDataDto> quarterDataDto : dataDtoHashMapInMap.entrySet()) {
            dataDtoHashMap.put(quarterDataDto.getKey(), addQuarterData(quarterDataDto.getValue(), dataDtoHashMap.get(quarterDataDto.getKey())));
        }
        return dataDtoHashMap;
    }

    private HashMap<String, TechnicalEquipmentSPRMothDataDto> addMonthDataDtoHashMaps(
            HashMap<String, TechnicalEquipmentSPRMothDataDto> dataDtoHashMapInMap,
            HashMap<String, TechnicalEquipmentSPRMothDataDto> dataDtoHashMap) {
        for (Map.Entry<String, TechnicalEquipmentSPRMothDataDto> mothDataDto : dataDtoHashMapInMap.entrySet()) {
            dataDtoHashMap.put(mothDataDto.getKey(), addMonthData(mothDataDto.getValue(), dataDtoHashMap.get(mothDataDto.getKey())));
        }
        return dataDtoHashMap;
    }

    private TechnicalEquipmentSPRTotalDataDto addTotalData(TechnicalEquipmentSPRTotalDataDto totalDataDtoInMap, TechnicalEquipmentSPRTotalDataDto totalDataDto) {
        if (Objects.isNull(totalDataDto)) return totalDataDtoInMap;
        return TechnicalEquipmentSPRTotalDataDto.builder()
                .yearData(totalDataDtoInMap.getYearData())
                .mothDataDtoHashMap(addMonthDataDtoHashMaps(totalDataDtoInMap.getMothDataDtoHashMap(), totalDataDto.getMothDataDtoHashMap()))
                .quarterDataDtoHashMap(addQuarterDataDtoHashMaps(totalDataDtoInMap.getQuarterDataDtoHashMap(), totalDataDto.getQuarterDataDtoHashMap()))
                .yearData(addYearData(totalDataDtoInMap.getYearData(), totalDataDto.getYearData()))
                .yearLimit(addYearLimit(totalDataDtoInMap.getYearLimit(), totalDataDto.getYearLimit()))
                .build();
    }

    private TechnicalEquipmentSPRTotalDataDto createTotalDataDto(TechnicalEquipmentSPRYearLimitDto yearLimit) {
        HashMap<String, TechnicalEquipmentSPRMothDataDto> mothDataDtoHashMap = new HashMap<>();
        HashMap<String, TechnicalEquipmentSPRQuarterDataDto> quarterDataDtoHashMap = new HashMap<>();
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
        TechnicalEquipmentSPRYearDataDto yearData = yearLimit.getYearData();
        yearData.setYearLimit(null);
        return TechnicalEquipmentSPRTotalDataDto.builder()
                .mothDataDtoHashMap(mothDataDtoHashMap)
                .quarterDataDtoHashMap(quarterDataDtoHashMap)
                .yearData(yearData)
                .yearLimit(yearLimit)
                .build();
    }

    BigDecimal addBigDemical(BigDecimal one, BigDecimal two) {
        if (Objects.isNull(one)) one = BigDecimal.ZERO;
        if (Objects.isNull(two)) two = BigDecimal.ZERO;
        return one.add(two);
    }
}
