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

import java.math.BigDecimal;
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
            FuelCombustionQuarterDataDto quarterDataDtoOne = null;
            FuelCombustionQuarterDataDto quarterDataDtoTwo = null;
            FuelCombustionQuarterDataDto quarterDataDtoThree = null;
            FuelCombustionQuarterDataDto quarterDataDtoFour = null;
            for (FuelCombustionQuarterDataDto quarterDataDto : quarterDataDtoList) {
                if (quarterDataDto.getQuarter().contains("-1")) {
                    quarterDataDtoOne = quarterDataDto;
                } else if (quarterDataDto.getQuarter().contains("-2")) {
                    quarterDataDtoTwo = quarterDataDto;
                } else if (quarterDataDto.getQuarter().contains("-3")) {
                    quarterDataDtoThree = quarterDataDto;
                } else if (quarterDataDto.getQuarter().contains("-4")) {
                    quarterDataDtoFour = quarterDataDto;
                }
            }
            List<FuelCombustionQuarterDataDto> quarterDataDtoListAnswer = new ArrayList<>();
            if (Objects.isNull(quarterDataDtoOne)) {
                quarterDataDtoOne = FuelCombustionQuarterDataDto.builder()
                        .quarter(yearLimitDto.getYear() + "-1")
                        .consumptionM3OnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionM3OnYear()))
                        .consumptionKgOnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionKgOnYear()))
                        .consumptionTonOnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionTonOnYear()))
                        .build();
            } else {
                quarterDataDtoOne.setConsumptionM3OnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionM3OnYear())
                        .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionM3OnMonth())));
                quarterDataDtoOne.setConsumptionKgOnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionKgOnYear())
                        .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionKgOnMonth())));
                quarterDataDtoOne.setConsumptionTonOnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionTonOnYear())
                        .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionTonOnMonth())));
            }

            if (Objects.isNull(quarterDataDtoTwo)) {
                assert quarterDataDtoOne != null;
                quarterDataDtoTwo = FuelCombustionQuarterDataDto.builder()
                        .quarter(yearLimitDto.getYear() + "-2")
                        .consumptionM3OnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionM3OnYear())
                                .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionM3OnMonth())))
                        .consumptionKgOnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionKgOnYear())
                                .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionKgOnMonth())))
                        .consumptionTonOnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionTonOnYear())
                                .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionTonOnMonth())))
                        .build();
            } else {
                assert quarterDataDtoOne != null;
                quarterDataDtoTwo.setConsumptionM3OnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionM3OnYear())
                        .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionM3OnMonth()))
                        .subtract(zeroIfNull(quarterDataDtoTwo.getConsumptionM3OnMonth())));
                quarterDataDtoTwo.setConsumptionKgOnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionKgOnYear())
                        .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionKgOnMonth()))
                        .subtract(zeroIfNull(quarterDataDtoTwo.getConsumptionKgOnMonth())));
                quarterDataDtoTwo.setConsumptionTonOnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionTonOnYear())
                        .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionTonOnMonth()))
                        .subtract(zeroIfNull(quarterDataDtoTwo.getConsumptionTonOnMonth())));
            }

            if (Objects.isNull(quarterDataDtoThree)) {
                assert quarterDataDtoTwo != null;
                quarterDataDtoThree = FuelCombustionQuarterDataDto.builder()
                        .quarter(yearLimitDto.getYear() + "-3")
                        .consumptionM3OnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionM3OnYear())
                                .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionM3OnMonth()))
                                .subtract(zeroIfNull(quarterDataDtoTwo.getConsumptionM3OnMonth())))
                        .consumptionKgOnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionKgOnYear())
                                .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionKgOnMonth()))
                                .subtract(zeroIfNull(quarterDataDtoTwo.getConsumptionKgOnMonth())))
                        .consumptionTonOnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionTonOnYear())
                                .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionTonOnMonth()))
                                .subtract(zeroIfNull(quarterDataDtoTwo.getConsumptionTonOnMonth())))
                        .build();
            } else {
                assert quarterDataDtoTwo != null;
                quarterDataDtoThree.setConsumptionM3OnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionM3OnYear())
                        .subtract(zeroIfNull(zeroIfNull(quarterDataDtoOne.getConsumptionM3OnMonth())))
                        .subtract(zeroIfNull(zeroIfNull(quarterDataDtoTwo.getConsumptionM3OnMonth())))
                        .subtract(zeroIfNull(zeroIfNull(quarterDataDtoThree.getConsumptionM3OnMonth()))));
                quarterDataDtoThree.setConsumptionKgOnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionKgOnYear())
                        .subtract(zeroIfNull(zeroIfNull(quarterDataDtoOne.getConsumptionKgOnMonth())))
                        .subtract(zeroIfNull(zeroIfNull(quarterDataDtoTwo.getConsumptionKgOnMonth())))
                        .subtract(zeroIfNull(zeroIfNull(quarterDataDtoThree.getConsumptionKgOnMonth()))));
                quarterDataDtoThree.setConsumptionTonOnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionTonOnYear())
                        .subtract(zeroIfNull(zeroIfNull(quarterDataDtoOne.getConsumptionTonOnMonth())))
                        .subtract(zeroIfNull(zeroIfNull(quarterDataDtoTwo.getConsumptionTonOnMonth())))
                        .subtract(zeroIfNull(zeroIfNull(quarterDataDtoThree.getConsumptionTonOnMonth()))));
            }

            if (Objects.isNull(quarterDataDtoFour)) {
                assert quarterDataDtoThree != null;
                quarterDataDtoFour = FuelCombustionQuarterDataDto.builder()
                        .quarter(yearLimitDto.getYear() + "-4")
                        .consumptionM3OnMonthLimit(zeroIfNull(zeroIfNull(yearLimitDto.getConsumptionM3OnYear()))
                                .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionM3OnMonth()))
                                .subtract(zeroIfNull(quarterDataDtoTwo.getConsumptionM3OnMonth()))
                                .subtract(zeroIfNull(quarterDataDtoThree.getConsumptionM3OnMonth())))
                        .consumptionKgOnMonthLimit(zeroIfNull(zeroIfNull(yearLimitDto.getConsumptionKgOnYear()))
                                .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionKgOnMonth()))
                                .subtract(zeroIfNull(quarterDataDtoTwo.getConsumptionKgOnMonth()))
                                .subtract(zeroIfNull(quarterDataDtoThree.getConsumptionKgOnMonth())))
                        .consumptionTonOnMonthLimit(zeroIfNull(zeroIfNull(yearLimitDto.getConsumptionTonOnYear()))
                                .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionTonOnMonth()))
                                .subtract(zeroIfNull(quarterDataDtoTwo.getConsumptionTonOnMonth()))
                                .subtract(zeroIfNull(quarterDataDtoThree.getConsumptionTonOnMonth())))
                        .build();
            } else {
                assert quarterDataDtoThree != null;
                quarterDataDtoFour.setConsumptionM3OnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionM3OnYear())
                        .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionM3OnMonth()))
                        .subtract(zeroIfNull(quarterDataDtoTwo.getConsumptionM3OnMonth()))
                        .subtract(zeroIfNull(quarterDataDtoThree.getConsumptionM3OnMonth()))
                        .subtract(zeroIfNull(quarterDataDtoFour.getConsumptionM3OnMonth())));
                quarterDataDtoFour.setConsumptionKgOnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionKgOnYear())
                        .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionKgOnMonth()))
                        .subtract(zeroIfNull(quarterDataDtoTwo.getConsumptionKgOnMonth()))
                        .subtract(zeroIfNull(quarterDataDtoThree.getConsumptionKgOnMonth()))
                        .subtract(zeroIfNull(quarterDataDtoFour.getConsumptionKgOnMonth())));
                quarterDataDtoFour.setConsumptionTonOnMonthLimit(zeroIfNull(yearLimitDto.getConsumptionTonOnYear())
                        .subtract(zeroIfNull(quarterDataDtoOne.getConsumptionTonOnMonth()))
                        .subtract(zeroIfNull(quarterDataDtoTwo.getConsumptionTonOnMonth()))
                        .subtract(zeroIfNull(quarterDataDtoThree.getConsumptionTonOnMonth()))
                        .subtract(zeroIfNull(quarterDataDtoFour.getConsumptionTonOnMonth())));
            }

            quarterDataDtoListAnswer.add(quarterDataDtoOne);
            quarterDataDtoListAnswer.add(quarterDataDtoTwo);
            quarterDataDtoListAnswer.add(quarterDataDtoThree);
            quarterDataDtoListAnswer.add(quarterDataDtoFour);
            yearLimitDto.setQuarterDataList(quarterDataDtoListAnswer);
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

    BigDecimal zeroIfNull(BigDecimal number) {
        if (Objects.isNull(number)) return BigDecimal.ZERO;
        return number;
    }
}
