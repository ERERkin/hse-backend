package kz.ccecc.hse_backend.mapper.fuelCombustionMapper;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionMothDataDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingMothData;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionMothData;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import kz.ccecc.hse_backend.mapper.batteryChargingMapper.BatteryChargingMothDataDtoMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class FuelCombustionMothDataDtoMapper
        extends AbstractMapper<FuelCombustionMothData, FuelCombustionMothDataDto> {
    public FuelCombustionMothDataDtoMapper(ModelMapper mapper) {
        super(mapper, FuelCombustionMothData.class, FuelCombustionMothDataDto.class);
        mapper.createTypeMap(FuelCombustionMothDataDto.class, FuelCombustionMothData.class).addMappings(mr -> {
            mr.using(new MonthStringToMonthDateConverter()).map(FuelCombustionMothDataDto::getMonth,
                    FuelCombustionMothData::setMonth);
        });

        mapper.createTypeMap(FuelCombustionMothData.class, FuelCombustionMothDataDto.class).addMappings(mr -> {
            mr.using(new MonthDateToMonthStringConverter()).map(FuelCombustionMothData::getMonth,
                    FuelCombustionMothDataDto::setMonth);
        });
    }

    @Override
    public FuelCombustionMothData toEntity(FuelCombustionMothDataDto dto) {
        return super.toEntity(dto);
    }

    @Override
    public FuelCombustionMothDataDto toDto(FuelCombustionMothData entity) {
        return super.toDto(entity);
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
