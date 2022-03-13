package kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRMothDataDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRMothData;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TechnicalEquipmentSPRMothDataMapper
        extends AbstractMapper<TechnicalEquipmentSPRMothData, TechnicalEquipmentSPRMothDataDto> {
    public TechnicalEquipmentSPRMothDataMapper(ModelMapper mapper) {
        super(mapper, TechnicalEquipmentSPRMothData.class, TechnicalEquipmentSPRMothDataDto.class);
    }
}
