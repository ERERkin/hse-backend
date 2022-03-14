package kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRFuelTypeDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRPollutionSourceDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRPollutionSource;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TechnicalEquipmentSPRPollutionSourceMapper
        extends AbstractMapper<TechnicalEquipmentSPRPollutionSource, TechnicalEquipmentSPRPollutionSourceDto> {
    public TechnicalEquipmentSPRPollutionSourceMapper(ModelMapper mapper) {
        super(mapper, TechnicalEquipmentSPRPollutionSource.class, TechnicalEquipmentSPRPollutionSourceDto.class);
    }

    @Autowired
    TechnicalEquipmentSPRFuelTypeMapper technicalEquipmentSPRFuelTypeMapper;

    @Override
    public TechnicalEquipmentSPRPollutionSourceDto toDto(TechnicalEquipmentSPRPollutionSource entity) {
        List<TechnicalEquipmentSPRFuelTypeDto> fuelTypeDtoList =
                technicalEquipmentSPRFuelTypeMapper.toDtos(entity.getFuelTypes());
        TechnicalEquipmentSPRPollutionSourceDto pollutionSourceDto = super.toDto(entity);
        pollutionSourceDto.setFuelTypes(fuelTypeDtoList);
        return pollutionSourceDto;
    }
}
