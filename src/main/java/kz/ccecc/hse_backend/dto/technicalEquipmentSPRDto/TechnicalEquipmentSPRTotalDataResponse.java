package kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "ППР тех. обрудования  (стравливание газа ) | TechnicalEquipmentSPRTotalDataDto", description = "объект данных итоги")
public class TechnicalEquipmentSPRTotalDataResponse {
    List<TechnicalEquipmentSPRMothDataDto> mothDataList;
    List<TechnicalEquipmentSPRQuarterDataDto> quarterDataList;
    TechnicalEquipmentSPRYearDataDto yearData;
    TechnicalEquipmentSPRYearLimitDto yearLimit;
}
