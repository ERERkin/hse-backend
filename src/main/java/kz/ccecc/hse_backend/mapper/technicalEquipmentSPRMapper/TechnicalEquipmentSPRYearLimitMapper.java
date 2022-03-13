package kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRYearLimitDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRYearLimit;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TechnicalEquipmentSPRYearLimitMapper
        extends AbstractMapper<TechnicalEquipmentSPRYearLimit, TechnicalEquipmentSPRYearLimitDto> {
    public TechnicalEquipmentSPRYearLimitMapper(ModelMapper mapper) {
        super(mapper, TechnicalEquipmentSPRYearLimit.class, TechnicalEquipmentSPRYearLimitDto.class);
    }
}
