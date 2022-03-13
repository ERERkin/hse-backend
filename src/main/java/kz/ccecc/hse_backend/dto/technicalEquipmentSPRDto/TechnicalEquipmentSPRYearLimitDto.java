package kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto;

import io.swagger.annotations.ApiModel;
import kz.ccecc.hse_backend.dto.base.AbstractDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRFuelType;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRMothData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@SuperBuilder
@ApiModel(value = "ППР тех. обрудования  (стравливание газа ) | TechnicalEquipmentSPRYearLimitDto", description = "объект данных о лимите")
public class TechnicalEquipmentSPRYearLimitDto extends AbstractDto {
    Long year;
    String count;
    BigDecimal volume;
    TechnicalEquipmentSPRFuelType fuelType;
    List<TechnicalEquipmentSPRMothData> mothDataList;
}
