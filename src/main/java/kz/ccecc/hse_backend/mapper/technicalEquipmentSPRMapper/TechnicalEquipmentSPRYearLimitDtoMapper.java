package kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRMothDataDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRQuarterDataDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRYearDataDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRYearLimitDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRYearLimit;
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
import java.util.regex.Pattern;

@Component
@Qualifier("modelMapper")
public class TechnicalEquipmentSPRYearLimitDtoMapper
        extends AbstractMapper<TechnicalEquipmentSPRYearLimit, TechnicalEquipmentSPRYearLimitDto> {
    public TechnicalEquipmentSPRYearLimitDtoMapper(ModelMapper mapper) {
        super(mapper, TechnicalEquipmentSPRYearLimit.class, TechnicalEquipmentSPRYearLimitDto.class);
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("yyyy-MM");
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    @Autowired
    TechnicalEquipmentSPRMothDataDtoMapper technicalEquipmentSPRMothDataDtoMapper;

    @Override
    public TechnicalEquipmentSPRYearLimitDto toDto(TechnicalEquipmentSPRYearLimit entity) {
        List<TechnicalEquipmentSPRMothDataDto> mothDataDtoList =
                technicalEquipmentSPRMothDataDtoMapper.toDtos(entity.getMothDataList());
        TechnicalEquipmentSPRYearLimitDto yearLimitDto = super.toDto(entity);
        yearLimitDto.setMothDataList(mothDataDtoList);
        addQuarterDataList(yearLimitDto, mothDataDtoList);
        addYearData(yearLimitDto, mothDataDtoList);
        return yearLimitDto;
    }

    private void addYearData(TechnicalEquipmentSPRYearLimitDto yearLimitDto, List<TechnicalEquipmentSPRMothDataDto> mothDataDtoList) {
        if (Objects.nonNull(yearLimitDto.getId()) &&
                Objects.nonNull(yearLimitDto.getYear())) {
            TechnicalEquipmentSPRYearDataDto yearDataDto = null;
            Boolean flag = true;
            for (TechnicalEquipmentSPRMothDataDto monthDataDto : mothDataDtoList) {
                if (flag) {
                    yearDataDto = TechnicalEquipmentSPRYearDataDto.builder()
                            .year(yearLimitDto.getYear())
                            .count(monthDataDto.getCount())
                            .volume(monthDataDto.getVolume())
                            .build();
                    flag = false;
                } else {
                    if (isNumeric(yearDataDto.getCount()) && isNumeric(monthDataDto.getCount())) {
                        yearDataDto.setCount(
                                BigDecimal.valueOf(Double.parseDouble(yearDataDto.getCount()))
                                        .add(BigDecimal.valueOf(Double.parseDouble(monthDataDto.getCount()))).toString());
                    } else {
                        yearDataDto.setCount(yearDataDto.getCount());
                    }
                    if (Objects.isNull(yearDataDto.getVolume()))
                        yearDataDto.setVolume(monthDataDto.getVolume());
                    else
                        yearDataDto.setVolume(yearDataDto.getVolume().add(monthDataDto.getVolume()));
                }
            }
            yearLimitDto.setYearData(yearDataDto);
        }
    }

    private void addQuarterDataList(TechnicalEquipmentSPRYearLimitDto yearLimitDto, List<TechnicalEquipmentSPRMothDataDto> mothDataDtoList) {
        if (Objects.nonNull(yearLimitDto.getId()) &&
                Objects.nonNull(yearLimitDto.getYear())) {
            List<TechnicalEquipmentSPRQuarterDataDto> quarterDataDtoList = new ArrayList<>();
            mothDataDtoList.forEach(monthDataDto -> {
                LocalDate month = LocalDate.parse(monthDataDto.getMonth() + "-01", formatter);
                Integer monthNum = month.getMonthValue();
                String quarter = "";
                TechnicalEquipmentSPRQuarterDataDto quarterDataDto;
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
                    quarterDataDto = TechnicalEquipmentSPRQuarterDataDto.builder()
                            .quarter(quarter)
                            .count(monthDataDto.getCount())
                            .volume(monthDataDto.getVolume())
                            .build();
                    quarterDataDtoList.add(quarterDataDto);
                } else {
                    Integer index = getIndexQuarterDataInList(quarter, quarterDataDtoList);
                    if (index >= 0) {
                        if (isNumeric(quarterDataDto.getCount()) && isNumeric(monthDataDto.getCount())) {
                            quarterDataDtoList.get(index).setCount(
                                    BigDecimal.valueOf(Double.parseDouble(quarterDataDto.getCount()))
                                            .add(BigDecimal.valueOf(Double.parseDouble(monthDataDto.getCount()))).toString());
                        } else {
                            quarterDataDtoList.get(index).setCount(quarterDataDto.getCount());
                        }
                        if (Objects.isNull(quarterDataDtoList.get(index).getVolume()))
                            quarterDataDtoList.get(index).setVolume(monthDataDto.getVolume());
                        else
                            quarterDataDtoList.get(index).setVolume(quarterDataDtoList.get(index).getVolume().add(monthDataDto.getVolume()));
                    }
                }
            });
            TechnicalEquipmentSPRQuarterDataDto quarterDataDtoOne = null;
            TechnicalEquipmentSPRQuarterDataDto quarterDataDtoTwo = null;
            TechnicalEquipmentSPRQuarterDataDto quarterDataDtoThree = null;
            TechnicalEquipmentSPRQuarterDataDto quarterDataDtoFour = null;

            for (TechnicalEquipmentSPRQuarterDataDto quarterDataDto : quarterDataDtoList) {
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

            List<TechnicalEquipmentSPRQuarterDataDto> quarterDataDtoListAnswer = new ArrayList<>();

            if (Objects.isNull(quarterDataDtoOne)) {
                quarterDataDtoOne = TechnicalEquipmentSPRQuarterDataDto.builder()
                        .quarter(yearLimitDto.getYear() + "-1")
                        .countLimit(yearLimitDto.getCount())
                        .volumeLimit(zeroIfNull(yearLimitDto.getVolume()))
                        .build();
            } else {
                if(isNumeric(yearLimitDto.getCount())){
                    quarterDataDtoOne.setCountLimit(stringToBigDemical(yearLimitDto.getCount())
                            .subtract(zeroIfNull(isNumeric(quarterDataDtoOne.getCount()) ?
                                    stringToBigDemical(quarterDataDtoOne.getCount()) : BigDecimal.ZERO)).toString());
                }
                quarterDataDtoOne.setVolumeLimit(zeroIfNull(yearLimitDto.getVolume())
                        .subtract(zeroIfNull(quarterDataDtoOne.getVolume())));
            }

            if (Objects.isNull(quarterDataDtoTwo)) {
                quarterDataDtoTwo = TechnicalEquipmentSPRQuarterDataDto.builder()
                        .quarter(yearLimitDto.getYear() + "-2")
                        .countLimit(isNumeric(yearLimitDto.getCount()) ?
                                stringToBigDemical(yearLimitDto.getCount())
                                        .subtract(zeroIfNull(isNumeric(quarterDataDtoOne.getCount()) ?
                                                stringToBigDemical(quarterDataDtoOne.getCount()) : BigDecimal.ZERO)).toString() :
                                yearLimitDto.getCount())
                        .volumeLimit(zeroIfNull(zeroIfNull(yearLimitDto.getVolume())
                                .subtract(zeroIfNull(quarterDataDtoOne.getVolume()))))
                        .build();
            } else {
                if(isNumeric(yearLimitDto.getCount())){
                    quarterDataDtoTwo.setCountLimit(stringToBigDemical(yearLimitDto.getCount())
                            .subtract(zeroIfNull(isNumeric(quarterDataDtoOne.getCount()) ?
                                    stringToBigDemical(quarterDataDtoOne.getCount()) : BigDecimal.ZERO))
                            .subtract(zeroIfNull(isNumeric(quarterDataDtoTwo.getCount()) ?
                                    stringToBigDemical(quarterDataDtoTwo.getCount()) : BigDecimal.ZERO)).toString());
                }
                quarterDataDtoTwo.setVolumeLimit(zeroIfNull(yearLimitDto.getVolume())
                        .subtract(zeroIfNull(quarterDataDtoOne.getVolume()))
                        .subtract(zeroIfNull(quarterDataDtoTwo.getVolume())));
            }

            if (Objects.isNull(quarterDataDtoThree)) {
                quarterDataDtoThree = TechnicalEquipmentSPRQuarterDataDto.builder()
                        .quarter(yearLimitDto.getYear() + "-3")
                        .countLimit(isNumeric(yearLimitDto.getCount()) ?
                                stringToBigDemical(yearLimitDto.getCount())
                                        .subtract(zeroIfNull(isNumeric(quarterDataDtoOne.getCount()) ?
                                                stringToBigDemical(quarterDataDtoOne.getCount()) : BigDecimal.ZERO))
                                        .subtract(zeroIfNull(isNumeric(quarterDataDtoTwo.getCount()) ?
                                                stringToBigDemical(quarterDataDtoTwo.getCount()) : BigDecimal.ZERO)).toString():
                                yearLimitDto.getCount())
                        .volumeLimit(zeroIfNull(zeroIfNull(yearLimitDto.getVolume())
                                .subtract(zeroIfNull(quarterDataDtoOne.getVolume()))
                                .subtract(zeroIfNull(quarterDataDtoTwo.getVolume()))))
                        .build();
            } else {
                if(isNumeric(yearLimitDto.getCount())){
                    quarterDataDtoThree.setCountLimit(stringToBigDemical(yearLimitDto.getCount())
                            .subtract(zeroIfNull(isNumeric(quarterDataDtoOne.getCount()) ?
                                    stringToBigDemical(quarterDataDtoOne.getCount()) : BigDecimal.ZERO))
                            .subtract(zeroIfNull(isNumeric(quarterDataDtoTwo.getCount()) ?
                                    stringToBigDemical(quarterDataDtoTwo.getCount()) : BigDecimal.ZERO))
                            .subtract(zeroIfNull(isNumeric(quarterDataDtoThree.getCount()) ?
                                    stringToBigDemical(quarterDataDtoThree.getCount()) : BigDecimal.ZERO)).toString());
                }
                quarterDataDtoThree.setVolumeLimit(zeroIfNull(yearLimitDto.getVolume())
                        .subtract(zeroIfNull(quarterDataDtoOne.getVolume()))
                        .subtract(zeroIfNull(quarterDataDtoTwo.getVolume()))
                        .subtract(zeroIfNull(quarterDataDtoThree.getVolume())));
            }

            if (Objects.isNull(quarterDataDtoFour)) {
                quarterDataDtoFour = TechnicalEquipmentSPRQuarterDataDto.builder()
                        .quarter(yearLimitDto.getYear() + "-4")
                        .countLimit(isNumeric(yearLimitDto.getCount()) ?
                                stringToBigDemical(yearLimitDto.getCount())
                                        .subtract(zeroIfNull(isNumeric(quarterDataDtoOne.getCount()) ?
                                                stringToBigDemical(quarterDataDtoOne.getCount()) : BigDecimal.ZERO))
                                        .subtract(zeroIfNull(isNumeric(quarterDataDtoTwo.getCount()) ?
                                                stringToBigDemical(quarterDataDtoTwo.getCount()) : BigDecimal.ZERO))
                                        .subtract(zeroIfNull(isNumeric(quarterDataDtoThree.getCount()) ?
                                                stringToBigDemical(quarterDataDtoThree.getCount()) : BigDecimal.ZERO)).toString():
                                yearLimitDto.getCount())
                        .volumeLimit(zeroIfNull(zeroIfNull(yearLimitDto.getVolume())
                                .subtract(zeroIfNull(quarterDataDtoOne.getVolume()))
                                .subtract(zeroIfNull(quarterDataDtoTwo.getVolume()))
                                .subtract(zeroIfNull(quarterDataDtoThree.getVolume()))))
                        .build();
            } else {
                if(isNumeric(yearLimitDto.getCount())){
                    quarterDataDtoFour.setCountLimit(stringToBigDemical(yearLimitDto.getCount())
                            .subtract(zeroIfNull(isNumeric(quarterDataDtoOne.getCount()) ?
                                    stringToBigDemical(quarterDataDtoOne.getCount()) : BigDecimal.ZERO))
                            .subtract(zeroIfNull(isNumeric(quarterDataDtoTwo.getCount()) ?
                                    stringToBigDemical(quarterDataDtoTwo.getCount()) : BigDecimal.ZERO))
                            .subtract(zeroIfNull(isNumeric(quarterDataDtoThree.getCount()) ?
                                    stringToBigDemical(quarterDataDtoThree.getCount()) : BigDecimal.ZERO))
                            .subtract(zeroIfNull(isNumeric(quarterDataDtoFour.getCount()) ?
                                    stringToBigDemical(quarterDataDtoFour.getCount()) : BigDecimal.ZERO)).toString());
                }
                quarterDataDtoFour.setVolumeLimit(zeroIfNull(yearLimitDto.getVolume())
                        .subtract(zeroIfNull(quarterDataDtoOne.getVolume()))
                        .subtract(zeroIfNull(quarterDataDtoTwo.getVolume()))
                        .subtract(zeroIfNull(quarterDataDtoThree.getVolume()))
                        .subtract(zeroIfNull(quarterDataDtoFour.getVolume())));
            }

            quarterDataDtoListAnswer.add(quarterDataDtoOne);
            quarterDataDtoListAnswer.add(quarterDataDtoTwo);
            quarterDataDtoListAnswer.add(quarterDataDtoThree);
            quarterDataDtoListAnswer.add(quarterDataDtoFour);
            yearLimitDto.setQuarterDataList(quarterDataDtoListAnswer);
        }
    }

    TechnicalEquipmentSPRQuarterDataDto getIfHasQuarterData(String quarter, List<TechnicalEquipmentSPRQuarterDataDto> quarterDataDtoList) {
        for (TechnicalEquipmentSPRQuarterDataDto quarterDataDto : quarterDataDtoList) {
            if (Objects.equals(quarter, quarterDataDto.getQuarter())) return quarterDataDto;
        }
        return null;
    }

    Integer getIndexQuarterDataInList(String quarter, List<TechnicalEquipmentSPRQuarterDataDto> quarterDataDtoList) {
        Integer index = -1;
        for (TechnicalEquipmentSPRQuarterDataDto quarterDataDto : quarterDataDtoList) {
            if (Objects.equals(quarter, quarterDataDto.getQuarter())) index++;
        }
        return index;
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    BigDecimal zeroIfNull(BigDecimal number) {
        if (Objects.isNull(number)) return BigDecimal.ZERO;
        return number;
    }

    BigDecimal stringToBigDemical(String num){
        return BigDecimal.valueOf(Double.parseDouble(num));
    }
}
