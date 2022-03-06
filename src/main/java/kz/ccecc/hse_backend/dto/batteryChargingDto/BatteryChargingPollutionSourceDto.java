package kz.ccecc.hse_backend.dto.batteryChargingDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.ccecc.hse_backend.dto.base.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@SuperBuilder
@ApiModel(value = "Зарядка аккумулятора | BatteryChargingPollutionSourceDto", description = "объект данных об источние выброса")
public class BatteryChargingPollutionSourceDto extends AbstractDto {
    @ApiModelProperty(notes = "Наименование источника выделения загрязняющих веществ ",name="name",value="Аккумуляторная АБК КС")
    String name;
    @ApiModelProperty(notes = "Наименование источника выделения загрязняющих веществ ",name="number",value="0106")
    String number;
    BatteryChargingProductionDto production;
    List<BatteryChargingYearLimitDto> yearLimits;
}
