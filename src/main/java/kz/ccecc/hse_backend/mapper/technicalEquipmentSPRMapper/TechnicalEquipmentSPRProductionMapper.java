package kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRPollutionSourceDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRProductionDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRProduction;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("modelMapper")
public class TechnicalEquipmentSPRProductionMapper
        extends AbstractMapper<TechnicalEquipmentSPRProduction, TechnicalEquipmentSPRProductionDto> {
    public TechnicalEquipmentSPRProductionMapper(ModelMapper mapper) {
        super(mapper, TechnicalEquipmentSPRProduction.class, TechnicalEquipmentSPRProductionDto.class);
    }

    @Autowired
    TechnicalEquipmentSPRPollutionSourceMapper technicalEquipmentSPRPollutionSourceMapper;

    @Override
    public TechnicalEquipmentSPRProductionDto toDto(TechnicalEquipmentSPRProduction entity) {
        List<TechnicalEquipmentSPRPollutionSourceDto> pollutionSourceDtoList =
                technicalEquipmentSPRPollutionSourceMapper.toDtos(entity.getPollutionSources());
        TechnicalEquipmentSPRProductionDto productionDto = super.toDto(entity);
        productionDto.setPollutionSources(pollutionSourceDtoList);
        return productionDto;
    }
}
