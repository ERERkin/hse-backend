package kz.ccecc.hse_backend.mapper.batteryChargingMapper;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearLimitDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingMothData;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Qualifier("modelMapper")
public class BatteryChargingMothDataDtoMapper
        extends AbstractMapper<BatteryChargingMothData, BatteryChargingMothDataDto> {
    public BatteryChargingMothDataDtoMapper( ModelMapper mapper) {
        super(mapper, BatteryChargingMothData.class, BatteryChargingMothDataDto.class);
        mapper.createTypeMap(BatteryChargingMothDataDto.class, BatteryChargingMothData.class).addMappings(mr -> {
            mr.using(new MonthStringToMonthDateConverter()).map(BatteryChargingMothDataDto::getMonth,
                    BatteryChargingMothData::setMonth);
        });

        mapper.createTypeMap(BatteryChargingMothData.class, BatteryChargingMothDataDto.class).addMappings(mr -> {
            mr.using(new MonthDateToMonthStringConverter()).map(BatteryChargingMothData::getMonth,
                    BatteryChargingMothDataDto::setMonth);
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
