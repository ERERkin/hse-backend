package kz.ccecc.hse_backend.service.technicalEquipmentSPRService.impl;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionPollutionSourceDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRPollutionSourceDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRProductionDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRProduction;
import kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper.TechnicalEquipmentSPRProductionMapper;
import kz.ccecc.hse_backend.repository.technicalEquipmentSPRRepository.TechnicalEquipmentSPRProductionRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRPollutionSourceService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TechnicalEquipmentSPRProductionServiceImpl extends AbstractService<TechnicalEquipmentSPRProduction,
        TechnicalEquipmentSPRProductionDto,
        TechnicalEquipmentSPRProductionRepository,
        TechnicalEquipmentSPRProductionMapper> implements TechnicalEquipmentSPRProductionService {
    public TechnicalEquipmentSPRProductionServiceImpl(TechnicalEquipmentSPRProductionRepository repository, TechnicalEquipmentSPRProductionMapper mapper) {
        super(repository, mapper, "technicalEquipmentSPRProduction");
    }

    @Autowired
    TechnicalEquipmentSPRPollutionSourceService technicalEquipmentSPRPollutionSourceService;

    @Override
    public TechnicalEquipmentSPRProductionDto save(TechnicalEquipmentSPRProductionDto item) {
        TechnicalEquipmentSPRProductionDto productionSaved = super.save(item);
        if(Objects.isNull(item.getPollutionSources())) return productionSaved;
        List<TechnicalEquipmentSPRPollutionSourceDto> pollutionSourceDtoList = new ArrayList<>();
        item.getPollutionSources().forEach(pollutionSourceDto -> {
            pollutionSourceDto.setProduction(productionSaved);
            TechnicalEquipmentSPRPollutionSourceDto pollutionSource = technicalEquipmentSPRPollutionSourceService.save(pollutionSourceDto);
            pollutionSource.setProduction(null);
            pollutionSourceDtoList.add(pollutionSource);
        });
        productionSaved.setPollutionSources(pollutionSourceDtoList);
        return super.save(item);
    }
}
