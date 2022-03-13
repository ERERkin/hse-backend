package kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRFuelTypeDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRFuelType;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TechnicalEquipmentSPRFuelTypeMapper
        extends AbstractMapper<TechnicalEquipmentSPRFuelType, TechnicalEquipmentSPRFuelTypeDto> {
    public TechnicalEquipmentSPRFuelTypeMapper(ModelMapper mapper) {
        super(mapper, TechnicalEquipmentSPRFuelType.class, TechnicalEquipmentSPRFuelTypeDto.class);
    }
}
