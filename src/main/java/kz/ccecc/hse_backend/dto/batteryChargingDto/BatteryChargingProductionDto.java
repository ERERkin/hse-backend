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
@ApiModel(value = "Зарядка аккумулятора | BatteryChargingProductionDto", description = "объект данных о производстве")
public class BatteryChargingProductionDto extends AbstractDto {
    @ApiModelProperty(notes = "Наименование производства, номер цеха, участка и т.п.",name="name",value="КС. Административно-бытовой корпус")
    String name;
    List<BatteryChargingPollutionSourceDto> pollutionSources;
}
