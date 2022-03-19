package kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "ППР тех. обрудования  (стравливание газа ) | TechnicalEquipmentSPRTotalDataDto", description = "объект данных итоги")
public class TechnicalEquipmentSPRTotalDataDto {
    HashMap<String, TechnicalEquipmentSPRMothDataDto> mothDataDtoHashMap;
    HashMap<String, TechnicalEquipmentSPRQuarterDataDto> quarterDataDtoHashMap;
    TechnicalEquipmentSPRYearDataDto yearData;
    TechnicalEquipmentSPRYearLimitDto yearLimit;
}
