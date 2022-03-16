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
    @Autowired
    FuelCombustionMothDataDtoMapper fuelCombustionMothDataDtoMapper;

    @Override
    public List<FuelCombustionTotalDataDto> getFuelCombustionTotal(Long year) {
        List<FuelCombustionYearLimit> yearLimitList = fuelCombustionYearLimitRepository.getFuelCombustionYearLimitByYear(year);
        List<FuelCombustionYearLimitDto> yearLimitDtoList = fuelCombustionYearLimitDtoMapper.toDtos(yearLimitList);
        List<String> categories = new ArrayList<>();
        categories = yearLimitList.stream().map(yearLimit -> {
            return yearLimit.getFuelType().getFuelCategory();
        }).collect(Collectors.toList());
        categories = new ArrayList<>(new HashSet<String>(categories));
        List<FuelCombustionTotalDataDto> totalDataDtoList = new ArrayList<>();
        yearLimitDtoList.forEach(yearLimit -> {
            if(isNew(yearLimit, totalDataDtoList)){
                HashMap<String, FuelCombustionMothDataDto> mothDataDtoHashMap = new HashMap<>();
                HashMap<String, FuelCombustionQuarterDataDto> quarterDataDtoHashMap = new HashMap<>();
                yearLimit.getMothDataList().forEach(mothData -> {
                    String month = mothData.getMonth();
                    if(mothDataDtoHashMap.containsKey(month)){
                        mothDataDtoHashMap.put(month, addMonthData(mothDataDtoHashMap.get(month), mothData));
                    }else {
                        mothDataDtoHashMap.put(month, mothData);
                    }
                });
                yearLimit.getQuarterDataList().forEach(quarterDataDto -> {
                    String quarter = quarterDataDto.getQuarter();
                    if(quarterDataDtoHashMap.containsKey(quarter)){
                        quarterDataDtoHashMap.put(quarter, addQuarterData(quarterDataDtoHashMap.get(quarter), quarterDataDto));
                    }else {
                        quarterDataDtoHashMap.put(quarter, quarterDataDto);
                    }
                });
                totalDataDtoList.add(FuelCombustionTotalDataDto.builder()
                                .category(yearLimit.getFuelType().getFuelCategory())
                                .mothDataDtoHashMap(mothDataDtoHashMap)
                                .quarterDataDtoHashMap(quarterDataDtoHashMap)
                                .yearData(yearLimit.getYearData())
                        .build());
            }else{
                
            }
        });
//        FuelCombustionTotalDataDto totalDataAnswer = new FuelCombustionTotalDataDto();
//        Boolean flag = true;
//        for(FuelCombustionTotalDataDto dataDto : totalDataDtoList){
//            if (flag) {
//                totalDataAnswer.setCategory(dataDto.getCategory());
//                totalDataAnswer.setMothDataDtoHashMap(dataDto.getMothDataDtoHashMap());
//                totalDataAnswer.setQuarterDataDtoHashMap(dataDto.getQuarterDataDtoHashMap());
//                totalDataAnswer.setYearData(dataDto.getYearData());
//            }else{
//                totalDataAnswer.s
//            }
//        }
        return totalDataDtoList;
    }

    private Boolean isNew(FuelCombustionYearLimitDto yearLimit, List<FuelCombustionTotalDataDto> totalDataDtoList){
        String category = yearLimit.getFuelType().getFuelCategory();
        for(FuelCombustionTotalDataDto totalDataDto : totalDataDtoList){
            if(Objects.equals(category, totalDataDto.getCategory())) return true;
        }
        return false;
    }

    private FuelCombustionMothDataDto addMonthData(FuelCombustionMothDataDto mothDataDtoInMap, FuelCombustionMothDataDto mothDataDto){
        return FuelCombustionMothDataDto.builder()
                .month(mothDataDto.getMonth())
                .consumptionM3OnMonth(addBigDemical(mothDataDtoInMap.getConsumptionM3OnMonth(), mothDataDto.getConsumptionKgOnMonth()))
                .consumptionKgOnMonth(addBigDemical(mothDataDtoInMap.getConsumptionKgOnMonth(), mothDataDto.getConsumptionKgOnMonth()))
                .consumptionLiterOnMonth(addBigDemical(mothDataDtoInMap.getConsumptionLiterOnMonth(), mothDataDto.getConsumptionLiterOnMonth()))
                .consumptionTonOnMonth(addBigDemical(mothDataDtoInMap.getConsumptionTonOnMonth(), mothDataDto.getConsumptionTonOnMonth()))
                .build();
    }

    private FuelCombustionQuarterDataDto addQuarterData(FuelCombustionQuarterDataDto quarterDataDtoInMap, FuelCombustionQuarterDataDto quarterDataDto){
        return FuelCombustionQuarterDataDto.builder()
                .quarter(quarterDataDto.getQuarter())
                .consumptionM3OnMonth(addBigDemical(quarterDataDtoInMap.getConsumptionM3OnMonth(), quarterDataDto.getConsumptionKgOnMonth()))
                .consumptionKgOnMonth(addBigDemical(quarterDataDtoInMap.getConsumptionKgOnMonth(), quarterDataDto.getConsumptionKgOnMonth()))
                .consumptionLiterOnMonth(addBigDemical(quarterDataDtoInMap.getConsumptionLiterOnMonth(), quarterDataDto.getConsumptionLiterOnMonth()))
                .consumptionTonOnMonth(addBigDemical(quarterDataDtoInMap.getConsumptionTonOnMonth(), quarterDataDto.getConsumptionTonOnMonth()))
                .build();
    }

    private FuelCombustionYearDataDto addYearData(FuelCombustionYearDataDto yearDataDtoInMap, FuelCombustionYearDataDto yearDataDto){
        return FuelCombustionYearDataDto.builder()
                .year(yearDataDto.getYear())
                .consumptionM3OnMonth(addBigDemical(yearDataDtoInMap.getConsumptionM3OnMonth(), yearDataDto.getConsumptionKgOnMonth()))
                .consumptionKgOnMonth(addBigDemical(yearDataDtoInMap.getConsumptionKgOnMonth(), yearDataDto.getConsumptionKgOnMonth()))
                .consumptionLiterOnMonth(addBigDemical(yearDataDtoInMap.getConsumptionLiterOnMonth(), yearDataDto.getConsumptionLiterOnMonth()))
                .consumptionTonOnMonth(addBigDemical(yearDataDtoInMap.getConsumptionTonOnMonth(), yearDataDto.getConsumptionTonOnMonth()))
                .build();
    }

    BigDecimal addBigDemical(BigDecimal one, BigDecimal two){
        return Objects.isNull(one) ?
                Objects.isNull(two) ? null : two :
                one.add(two);
    }
}
