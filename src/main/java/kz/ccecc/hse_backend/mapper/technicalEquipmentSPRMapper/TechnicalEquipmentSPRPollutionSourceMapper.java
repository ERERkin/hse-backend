package kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRPollutionSourceDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRPollutionSource;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TechnicalEquipmentSPRPollutionSourceMapper
        extends AbstractMapper<TechnicalEquipmentSPRPollutionSource, TechnicalEquipmentSPRPollutionSourceDto> {
    public TechnicalEquipmentSPRPollutionSourceMapper(ModelMapper mapper) {
        super(mapper, TechnicalEquipmentSPRPollutionSource.class, TechnicalEquipmentSPRPollutionSourceDto.class);
    }
}
