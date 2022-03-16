package kz.ccecc.hse_backend.mapper.batteryChargingMapper;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingQuarterDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearLimitDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionYearLimitDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingYearLimit;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionYearLimit;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Qualifier("modelMapper")
public class BatteryChargingYearLimitDtoMapper
        extends AbstractMapper<BatteryChargingYearLimit, BatteryChargingYearLimitDto> {
    public BatteryChargingYearLimitDtoMapper(ModelMapper mapper) {
        super(mapper, BatteryChargingYearLimit.class, BatteryChargingYearLimitDto.class);
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("yyyy-MM");

    @Autowired
    BatteryChargingMothDataDtoMapper batteryChargingMothDataDtoMapper;

    @Override
    public BatteryChargingYearLimitDto toDto(BatteryChargingYearLimit entity) {
        List<BatteryChargingMothDataDto> mothDataDtoList =
                batteryChargingMothDataDtoMapper
                        .toDtos(entity.getMothDataList());
        BatteryChargingYearLimitDto yearLimitDto = super.toDto(entity);
        yearLimitDto.setMothDataList(mothDataDtoList);
        addQuarterDataList(yearLimitDto, mothDataDtoList);
        addYearData(yearLimitDto, mothDataDtoList);
        return yearLimitDto;
    }

    private void addYearData(BatteryChargingYearLimitDto yearLimitDto, List<BatteryChargingMothDataDto> mothDataDtoList) {
        if (Objects.nonNull(yearLimitDto.getId()) &&
                Objects.nonNull(yearLimitDto.getYear())) {
            BatteryChargingYearDataDto yearDataDto = null;
            Boolean flag = true;
            for(BatteryChargingMothDataDto monthDataDto : mothDataDtoList){
                if(flag){
                    yearDataDto = BatteryChargingYearDataDto.builder()
                            .year(yearLimitDto.getYear())
                            .workTime(monthDataDto.getWorkTime())
                            .batteryCount(monthDataDto.getBatteryCount())
                            .batteryCapacity(monthDataDto.getBatteryCapacity())
                            .build();
                    flag = false;
                }else {
                    yearDataDto.setWorkTime(yearDataDto.getWorkTime() + monthDataDto.getWorkTime());
                    yearDataDto.setBatteryCount(yearDataDto.getBatteryCount() + monthDataDto.getBatteryCount());
                    yearDataDto.setBatteryCapacity(yearDataDto.getBatteryCapacity() + monthDataDto.getBatteryCapacity());
                }
            }
            List<BatteryChargingYearDataDto> yearDataDtoList = new ArrayList<>();
            yearDataDtoList.add(yearDataDto);
            yearLimitDto.setYearData(yearDataDtoList);
        }
    }

    private void addQuarterDataList(BatteryChargingYearLimitDto yearLimitDto, List<BatteryChargingMothDataDto> mothDataDtoList) {
        if (Objects.nonNull(yearLimitDto.getId()) &&
                Objects.nonNull(yearLimitDto.getYear())) {
            List<BatteryChargingQuarterDataDto> quarterDataDtoList = new ArrayList<>();
            mothDataDtoList.forEach(monthDataDto -> {
                LocalDate month = LocalDate.parse(monthDataDto.getMonth() + "-01", formatter);
                Integer monthNum = month.getMonthValue();
                String quarter = "";
                BatteryChargingQuarterDataDto quarterDataDto;
                if (monthNum == 1 || monthNum == 2 || monthNum == 3) {
                    quarter = month.getYear() + "-" + 1;
                } else if (monthNum == 4 || monthNum == 5 || monthNum == 6) {
                    quarter = month.getYear() + "-" + 2;
                } else if (monthNum == 7 || monthNum == 8 || monthNum == 9) {
                    quarter = month.getYear() + "-" + 3;
                } else {
                    quarter = month.getYear() + "-" + 4;
                }
                quarterDataDto = getIfHasQuarterData(quarter, quarterDataDtoList);
                if (Objects.isNull(quarterDataDto)) {
                    quarterDataDto = BatteryChargingQuarterDataDto.builder()
                            .quarter(quarter)
                            .workTime(monthDataDto.getWorkTime())
                            .batteryCount(monthDataDto.getBatteryCount())
                            .batteryCapacity(monthDataDto.getBatteryCapacity())
                            .build();
                    quarterDataDtoList.add(quarterDataDto);
                } else {
                    Integer index = getIndexQuarterDataInList(quarter, quarterDataDtoList);
                    if (index >= 0) {
                        quarterDataDtoList.get(index).setWorkTime(quarterDataDto.getWorkTime() + monthDataDto.getWorkTime());
                        quarterDataDtoList.get(index).setBatteryCount(quarterDataDto.getBatteryCount() + monthDataDto.getBatteryCount());
                        quarterDataDtoList.get(index).setBatteryCapacity(quarterDataDto.getBatteryCapacity() + monthDataDto.getBatteryCapacity());
                    }
                }

            });
            yearLimitDto.setQuarterDataList(quarterDataDtoList);
        }
    }

    BatteryChargingQuarterDataDto getIfHasQuarterData(String quarter, List<BatteryChargingQuarterDataDto> quarterDataDtoList) {
        for (BatteryChargingQuarterDataDto quarterDataDto : quarterDataDtoList) {
            if (Objects.equals(quarter, quarterDataDto.getQuarter())) return quarterDataDto;
        }
        return null;
    }

    Integer getIndexQuarterDataInList(String quarter, List<BatteryChargingQuarterDataDto> quarterDataDtoList) {
        Integer index = -1;
        for (BatteryChargingQuarterDataDto quarterDataDto : quarterDataDtoList) {
            if (Objects.equals(quarter, quarterDataDto.getQuarter())) index++;
        }
        return index;
    }
}
