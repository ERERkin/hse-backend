package kz.ccecc.hse_backend.dto.batteryChargingDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.ccecc.hse_backend.dto.base.AbstractDto;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingMothData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@SuperBuilder
@ApiModel(value = "Зарядка аккумулятора | BatteryChargingYearLimitDto", description = "объект данных о лимите")
public class BatteryChargingYearLimitDto extends AbstractDto {
    @ApiModelProperty(notes = "ЛИМИТ ПО ПРОЕКТУ ПДВ НА **** ГОД",name="batteryModel",value="2021")
    Long year;
    @ApiModelProperty(notes = "2V 120-12 (Powerstar)",name="batteryModel",value="2V 120-12 (Powerstar)")
    String batteryModel;
    @ApiModelProperty(notes = "Время  работы оборудования | ч/год ",name="workTime",value="8760")
    Double workTime;
    @ApiModelProperty(notes = "количество аккумуляторов, шт. | ед.",name="batteryCount",value="32")
    Long batteryCount;
    @ApiModelProperty(notes = "емкость аккумуляторов | А*ч",name="batteryCapacity",value="120")
    Double batteryCapacity;
    BatteryChargingPollutionSourceDto pollutionSource;
    List<BatteryChargingMothDataDto> mothDataList;
    List<BatteryChargingQuarterDataDto> quarterDataList;
    BatteryChargingYearDataDto yearData;
}
