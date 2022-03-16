package kz.ccecc.hse_backend.mapper.fuelCombustionMapper;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionMothDataDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionQuarterDataDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionYearDataDto;
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
        addQuarterDataList(yearLimitDto, mothDataDtoList);
        addYearData(yearLimitDto, mothDataDtoList);
        return yearLimitDto;
    }

    private void addTotal() {

    }

    private void addYearData(FuelCombustionYearLimitDto yearLimitDto, List<FuelCombustionMothDataDto> mothDataDtoList) {
        if (Objects.nonNull(yearLimitDto.getId()) &&
                Objects.nonNull(yearLimitDto.getYear())) {
            FuelCombustionYearDataDto yearDataDto = null;
            Boolean flag = true;
            for (FuelCombustionMothDataDto monthDataDto : mothDataDtoList) {
                if (flag) {
                    yearDataDto = FuelCombustionYearDataDto.builder()
                            .year(yearLimitDto.getYear())
                            .workTime(monthDataDto.getWorkTime())
                            .consumptionKgOnMonth(monthDataDto.getConsumptionKgOnMonth())
                            .consumptionLiterOnMonth(monthDataDto.getConsumptionLiterOnMonth())
                            .consumptionTonOnMonth(monthDataDto.getConsumptionTonOnMonth())
                            .consumptionM3OnMonth(monthDataDto.getConsumptionM3OnMonth())
                            .build();
                    flag = false;
                } else {
                    yearDataDto.setYear(yearDataDto.getYear());

                    if (Objects.isNull(yearDataDto.getWorkTime()))
                        yearDataDto.setWorkTime(monthDataDto.getWorkTime());
                    else
                        yearDataDto.setWorkTime(yearDataDto.getWorkTime().add(monthDataDto.getWorkTime()));

                    if (Objects.isNull(yearDataDto.getConsumptionM3OnMonth()))
                        yearDataDto.setConsumptionM3OnMonth(monthDataDto.getConsumptionM3OnMonth());
                    else
                        yearDataDto.setConsumptionM3OnMonth(yearDataDto.getConsumptionM3OnMonth().add(monthDataDto.getConsumptionM3OnMonth()));

                    if (Objects.isNull(yearDataDto.getConsumptionKgOnMonth()))
                        yearDataDto.setConsumptionKgOnMonth(monthDataDto.getConsumptionKgOnMonth());
                    else
                        yearDataDto.setConsumptionKgOnMonth(yearDataDto.getConsumptionKgOnMonth().add(monthDataDto.getConsumptionKgOnMonth()));

                    if (Objects.isNull(yearDataDto.getConsumptionLiterOnMonth()))
                        yearDataDto.setConsumptionLiterOnMonth(monthDataDto.getConsumptionLiterOnMonth());
                    else
                        yearDataDto.setConsumptionLiterOnMonth(yearDataDto.getConsumptionLiterOnMonth().add(monthDataDto.getConsumptionLiterOnMonth()));

                    if (Objects.isNull(yearDataDto.getConsumptionTonOnMonth()))
                        yearDataDto.setConsumptionTonOnMonth(monthDataDto.getConsumptionTonOnMonth());
                    else
                        yearDataDto.setConsumptionTonOnMonth(yearDataDto.getConsumptionTonOnMonth().add(monthDataDto.getConsumptionTonOnMonth()));
                }
            }
            yearLimitDto.setYearData(yearDataDto);
        }
    }

    private void addQuarterDataList(FuelCombustionYearLimitDto yearLimitDto, List<FuelCombustionMothDataDto> mothDataDtoList) {
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

                        if (Objects.isNull(quarterDataDtoList.get(index).getWorkTime()))
                            quarterDataDtoList.get(index).setWorkTime(monthDataDto.getWorkTime());
                        else
                            quarterDataDtoList.get(index).setWorkTime(quarterDataDtoList.get(index).getWorkTime().add(monthDataDto.getWorkTime()));

                        if (Objects.isNull(quarterDataDtoList.get(index).getConsumptionM3OnMonth()))
                            quarterDataDtoList.get(index).setConsumptionM3OnMonth(monthDataDto.getConsumptionM3OnMonth());
                        else
                            quarterDataDtoList.get(index).setConsumptionM3OnMonth(quarterDataDtoList.get(index).getConsumptionM3OnMonth().add(monthDataDto.getConsumptionM3OnMonth()));

                        if (Objects.isNull(quarterDataDtoList.get(index).getConsumptionKgOnMonth()))
                            quarterDataDtoList.get(index).setConsumptionKgOnMonth(monthDataDto.getConsumptionKgOnMonth());
                        else
                            quarterDataDtoList.get(index).setConsumptionKgOnMonth(quarterDataDtoList.get(index).getConsumptionKgOnMonth().add(monthDataDto.getConsumptionKgOnMonth()));

                        if (Objects.isNull(quarterDataDtoList.get(index).getConsumptionLiterOnMonth()))
                            quarterDataDtoList.get(index).setConsumptionLiterOnMonth(monthDataDto.getConsumptionLiterOnMonth());
                        else
                            quarterDataDtoList.get(index).setConsumptionLiterOnMonth(quarterDataDtoList.get(index).getConsumptionLiterOnMonth().add(monthDataDto.getConsumptionLiterOnMonth()));

                        if (Objects.isNull(quarterDataDtoList.get(index).getConsumptionTonOnMonth()))
                            quarterDataDtoList.get(index).setConsumptionTonOnMonth(monthDataDto.getConsumptionTonOnMonth());
                        else
                            quarterDataDtoList.get(index).setConsumptionTonOnMonth(quarterDataDtoList.get(index).getConsumptionTonOnMonth().add(monthDataDto.getConsumptionTonOnMonth()));
                    }
                }
            });
            yearLimitDto.setQuarterDataList(quarterDataDtoList);
        }
    }

    private FuelCombustionQuarterDataDto getIfHasQuarterData(String quarter, List<FuelCombustionQuarterDataDto> quarterDataDtoList) {
        for (FuelCombustionQuarterDataDto quarterDataDto : quarterDataDtoList) {
            if (Objects.equals(quarter, quarterDataDto.getQuarter())) return quarterDataDto;
        }
        return null;
    }

    private Integer getIndexQuarterDataInList(String quarter, List<FuelCombustionQuarterDataDto> quarterDataDtoList) {
        Integer index = -1;
        for (FuelCombustionQuarterDataDto quarterDataDto : quarterDataDtoList) {
            if (Objects.equals(quarter, quarterDataDto.getQuarter())) index++;
        }
        return index;
    }
}
