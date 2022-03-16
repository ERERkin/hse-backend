package kz.ccecc.hse_backend.dto.batteryChargingDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.ccecc.hse_backend.dto.base.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@SuperBuilder
@ApiModel(value = "Зарядка аккумулятора | BatteryChargingQuarterDataDto", description = "объект данных за квартал")
public class BatteryChargingQuarterDataDto extends AbstractDto {
    @ApiModelProperty(notes = "Месяц",name="workTime",value="2022-1")
    String quarter;
    @ApiModelProperty(notes = "Время  работы оборудования | ч/год ",name="workTime",value="730")
    Double workTime;
    @ApiModelProperty(notes = "количество аккумуляторов, шт. | ед.",name="batteryCount",value="32")
    Long batteryCount;
    @ApiModelProperty(notes = "емкость аккумуляторов | А*ч",name="batteryCapacity",value="120")
    Double batteryCapacity;
    BatteryChargingYearLimitDto yearLimit;
}
