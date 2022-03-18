package kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto;

import io.swagger.annotations.ApiModel;
import kz.ccecc.hse_backend.dto.base.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@SuperBuilder
@ApiModel(value = "ППР тех. обрудования  (стравливание газа ) | TechnicalEquipmentSPRQuarterDataDto", description = "объект данных за квартал")
public class TechnicalEquipmentSPRQuarterDataDto extends AbstractDto {
    String quarter;
    String count;
    BigDecimal volume;
    TechnicalEquipmentSPRYearLimitDto yearLimit;
    String countLimit;
    BigDecimal volumeLimit;
}
