package kz.ccecc.hse_backend.mapper.batteryChargingMapper;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingMothData;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Qualifier("modelMapper")
public class BatteryChargingMothDataToBatteryChargingMothDataDtoMapper
        extends AbstractMapper<BatteryChargingMothData, BatteryChargingMothDataDto> {
    public BatteryChargingMothDataToBatteryChargingMothDataDtoMapper(ModelMapper mapper) {
        super(mapper, BatteryChargingMothData.class, BatteryChargingMothDataDto.class);
    }

    @Autowired
    BatteryChargingYearLimitToBatteryChargingYearLimitDtoMapper batteryChargingYearLimitToBatteryChargingYearLimitDtoMapper;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("yyyy-MM");

    @Override
    public BatteryChargingMothData toEntity(BatteryChargingMothDataDto dto) {
        LocalDate localDate = LocalDate.parse(dto.getMonth() + "-01", formatter);
        return BatteryChargingMothData.builder()
                .id(dto.getId())
                .batteryCount(dto.getBatteryCount())
                .batteryCapacity(dto.getBatteryCapacity())
                .workTime(dto.getWorkTime())
                .month(localDate)
                .yearLimit(batteryChargingYearLimitToBatteryChargingYearLimitDtoMapper.toEntity(dto.getYearLimit()))
                .build();
    }

    @Override
    public BatteryChargingMothDataDto toDto(BatteryChargingMothData entity) {
        String month = formatterMonth.format(entity.getMonth());
        return BatteryChargingMothDataDto.builder()
                .id(entity.getId())
                .batteryCount(entity.getBatteryCount())
                .batteryCapacity(entity.getBatteryCapacity())
                .workTime(entity.getWorkTime())
                .month(month)
                .yearLimit(batteryChargingYearLimitToBatteryChargingYearLimitDtoMapper.toDto(entity.getYearLimit()))
                .build();
    }
}
