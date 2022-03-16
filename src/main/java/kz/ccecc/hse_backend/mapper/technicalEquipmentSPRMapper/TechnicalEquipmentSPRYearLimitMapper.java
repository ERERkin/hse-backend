package kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionQuarterDataDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRMothDataDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRQuarterDataDto;
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
public class TechnicalEquipmentSPRYearLimitMapper
        extends AbstractMapper<TechnicalEquipmentSPRYearLimit, TechnicalEquipmentSPRYearLimitDto> {
    public TechnicalEquipmentSPRYearLimitMapper(ModelMapper mapper) {
        super(mapper, TechnicalEquipmentSPRYearLimit.class, TechnicalEquipmentSPRYearLimitDto.class);
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("yyyy-MM");
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    @Autowired
    TechnicalEquipmentSPRMothDataMapper technicalEquipmentSPRMothDataMapper;

    @Override
    public TechnicalEquipmentSPRYearLimitDto toDto(TechnicalEquipmentSPRYearLimit entity) {
        List<TechnicalEquipmentSPRMothDataDto> mothDataDtoList =
                technicalEquipmentSPRMothDataMapper.toDtos(entity.getMothDataList());
        TechnicalEquipmentSPRYearLimitDto yearLimitDto = super.toDto(entity);
        yearLimitDto.setMothDataList(mothDataDtoList);
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
                        if(isNumeric(quarterDataDto.getCount()) && isNumeric(monthDataDto.getCount())){
                            quarterDataDtoList.get(index).setCount(
                                    BigDecimal.valueOf(Double.parseDouble(quarterDataDto.getCount()))
                                            .add(BigDecimal.valueOf(Double.parseDouble(monthDataDto.getCount()))).toString());
                        }else{
                            quarterDataDtoList.get(index).setCount(quarterDataDto.getCount());
                        }
                        quarterDataDtoList.get(index).setVolume(quarterDataDto.getVolume().add(monthDataDto.getVolume()));
                   }
                }
            });
            yearLimitDto.setQuarterDataList(quarterDataDtoList);
        }
        return yearLimitDto;
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
}
