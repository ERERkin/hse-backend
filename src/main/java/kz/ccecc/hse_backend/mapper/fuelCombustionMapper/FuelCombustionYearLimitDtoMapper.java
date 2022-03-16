package kz.ccecc.hse_backend.mapper.fuelCombustionMapper;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingQuarterDataDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionMothDataDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionQuarterDataDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionYearLimitDto;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionYearLimit;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
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
public class FuelCombustionYearLimitDtoMapper
        extends AbstractMapper<FuelCombustionYearLimit, FuelCombustionYearLimitDto> {
    public FuelCombustionYearLimitDtoMapper(ModelMapper mapper) {
        super(mapper, FuelCombustionYearLimit.class, FuelCombustionYearLimitDto.class);
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("yyyy-MM");

    @Autowired
    FuelCombustionMothDataDtoMapper fuelCombustionMothDataDtoMapper;

    @Override
    public FuelCombustionYearLimitDto toDto(FuelCombustionYearLimit entity) {
        List<FuelCombustionMothDataDto> mothDataDtoList =
                fuelCombustionMothDataDtoMapper.toDtos(entity.getMothDataList());
        FuelCombustionYearLimitDto yearLimitDto = super.toDto(entity);
        yearLimitDto.setMothDataList(mothDataDtoList);
        if (Objects.nonNull(yearLimitDto.getId()) &&
                Objects.nonNull(yearLimitDto.getYear())) {
            List<FuelCombustionQuarterDataDto> quarterDataDtoList = new ArrayList<>();
            mothDataDtoList.forEach(monthDataDto -> {
                LocalDate month = LocalDate.parse(monthDataDto.getMonth() + "-01", formatter);
                Integer monthNum = month.getMonthValue();
                String quarter = "";
                FuelCombustionQuarterDataDto quarterDataDto;
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
                    quarterDataDto = FuelCombustionQuarterDataDto.builder()
                            .quarter(quarter)
                            .workTime(monthDataDto.getWorkTime())
                            .consumptionKgOnMonth(monthDataDto.getConsumptionKgOnMonth())
                            .consumptionLiterOnMonth(monthDataDto.getConsumptionLiterOnMonth())
                            .consumptionTonOnMonth(monthDataDto.getConsumptionTonOnMonth())
                            .consumptionM3OnMonth(monthDataDto.getConsumptionM3OnMonth())
                            .build();
                    quarterDataDtoList.add(quarterDataDto);
                } else {
                    Integer index = getIndexQuarterDataInList(quarter, quarterDataDtoList);
                    if (index >= 0) {
                        quarterDataDtoList.get(index).setWorkTime(quarterDataDto.getWorkTime().add(monthDataDto.getWorkTime()));
                        quarterDataDtoList.get(index).setConsumptionM3OnMonth(quarterDataDto.getConsumptionM3OnMonth().add(monthDataDto.getConsumptionTonOnMonth()));
                        quarterDataDtoList.get(index).setConsumptionKgOnMonth(quarterDataDto.getConsumptionKgOnMonth().add(monthDataDto.getConsumptionKgOnMonth()));
                        quarterDataDtoList.get(index).setConsumptionLiterOnMonth(quarterDataDto.getConsumptionLiterOnMonth().add(monthDataDto.getConsumptionLiterOnMonth()));
                        quarterDataDtoList.get(index).setConsumptionTonOnMonth(quarterDataDto.getConsumptionTonOnMonth().add(monthDataDto.getConsumptionTonOnMonth()));
                    }
                }
            });
            yearLimitDto.setQuarterDataList(quarterDataDtoList);
        }
        return yearLimitDto;
    }

    FuelCombustionQuarterDataDto getIfHasQuarterData(String quarter, List<FuelCombustionQuarterDataDto> quarterDataDtoList) {
        for (FuelCombustionQuarterDataDto quarterDataDto : quarterDataDtoList) {
            if (Objects.equals(quarter, quarterDataDto.getQuarter())) return quarterDataDto;
        }
        return null;
    }

    Integer getIndexQuarterDataInList(String quarter, List<FuelCombustionQuarterDataDto> quarterDataDtoList) {
        Integer index = -1;
        for (FuelCombustionQuarterDataDto quarterDataDto : quarterDataDtoList) {
            if (Objects.equals(quarter, quarterDataDto.getQuarter())) index++;
        }
        return index;
    }
}
