package kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRFuelTypeDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRPollutionSourceDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRPollutionSource;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("modelMapper")
public class TechnicalEquipmentSPRPollutionSourceDtoMapper
        extends AbstractMapper<TechnicalEquipmentSPRPollutionSource, TechnicalEquipmentSPRPollutionSourceDto> {
    public TechnicalEquipmentSPRPollutionSourceDtoMapper(ModelMapper mapper) {
        super(mapper, TechnicalEquipmentSPRPollutionSource.class, TechnicalEquipmentSPRPollutionSourceDto.class);
    }

    @Autowired
    TechnicalEquipmentSPRFuelTypeDtoMapper technicalEquipmentSPRFuelTypeDtoMapper;

    @Override
    public TechnicalEquipmentSPRPollutionSourceDto toDto(TechnicalEquipmentSPRPollutionSource entity) {
        List<TechnicalEquipmentSPRFuelTypeDto> fuelTypeDtoList =
                technicalEquipmentSPRFuelTypeDtoMapper.toDtos(entity.getFuelTypes());
        TechnicalEquipmentSPRPollutionSourceDto pollutionSourceDto = super.toDto(entity);
        pollutionSourceDto.setFuelTypes(fuelTypeDtoList);
        return pollutionSourceDto;
    }
}
