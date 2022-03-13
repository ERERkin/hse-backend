package kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRProductionDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRProduction;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TechnicalEquipmentSPRProductionMapper
        extends AbstractMapper<TechnicalEquipmentSPRProduction, TechnicalEquipmentSPRProductionDto> {
    public TechnicalEquipmentSPRProductionMapper(ModelMapper mapper) {
        super(mapper, TechnicalEquipmentSPRProduction.class, TechnicalEquipmentSPRProductionDto.class);
    }
}
