package kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto;

import io.swagger.annotations.ApiModel;
import kz.ccecc.hse_backend.dto.base.AbstractDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRPollutionSource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@SuperBuilder
@ApiModel(value = "ППР тех. обрудования  (стравливание газа ) | TechnicalEquipmentSPRProductionDto", description = "объект данных о производстве")
public class TechnicalEquipmentSPRProductionDto extends AbstractDto {
    String name;
    List<TechnicalEquipmentSPRPollutionSourceDto> pollutionSources;
}
