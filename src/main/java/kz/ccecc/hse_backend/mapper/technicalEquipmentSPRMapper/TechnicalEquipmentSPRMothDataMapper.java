package kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionMothDataDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRMothDataDto;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionMothData;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRMothData;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import kz.ccecc.hse_backend.mapper.fuelCombustionMapper.FuelCombustionMothDataDtoMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Qualifier("modelMapper")
public class TechnicalEquipmentSPRMothDataMapper
        extends AbstractMapper<TechnicalEquipmentSPRMothData, TechnicalEquipmentSPRMothDataDto> {
    public TechnicalEquipmentSPRMothDataMapper(ModelMapper mapper) {
        super(mapper, TechnicalEquipmentSPRMothData.class, TechnicalEquipmentSPRMothDataDto.class);
        mapper.createTypeMap(TechnicalEquipmentSPRMothDataDto.class, TechnicalEquipmentSPRMothData.class).addMappings(mr -> {
            mr.using(new MonthStringToMonthDateConverter()).map(TechnicalEquipmentSPRMothDataDto::getMonth,
                    TechnicalEquipmentSPRMothData::setMonth);
        });

        mapper.createTypeMap(TechnicalEquipmentSPRMothData.class, TechnicalEquipmentSPRMothDataDto.class).addMappings(mr -> {
            mr.using(new MonthDateToMonthStringConverter()).map(TechnicalEquipmentSPRMothData::getMonth,
                    TechnicalEquipmentSPRMothDataDto::setMonth);
        });
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("yyyy-MM");

    public class MonthStringToMonthDateConverter extends AbstractConverter<String, LocalDate> {
        @Override
        protected LocalDate convert(String source) {
            return LocalDate.parse(source + "-01", formatter);
        }
    }

    public class MonthDateToMonthStringConverter extends AbstractConverter<LocalDate, String> {
        @Override
        protected String convert(LocalDate localDate) {
            return formatterMonth.format(localDate);
        }
    }
}
