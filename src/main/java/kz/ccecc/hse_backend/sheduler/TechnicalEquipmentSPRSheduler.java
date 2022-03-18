package kz.ccecc.hse_backend.sheduler;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRYearLimitDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRYearLimit;
import kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper.TechnicalEquipmentSPRYearLimitDtoMapper;
import kz.ccecc.hse_backend.repository.technicalEquipmentSPRRepository.TechnicalEquipmentSPRYearLimitRepository;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRYearLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TechnicalEquipmentSPRSheduler {
    @Autowired
    TechnicalEquipmentSPRYearLimitService technicalEquipmentSPRYearLimitService;
    @Autowired
    TechnicalEquipmentSPRYearLimitRepository technicalEquipmentSPRYearLimitRepository;
    @Autowired
    TechnicalEquipmentSPRYearLimitDtoMapper technicalEquipmentSPRYearLimitDtoMapper;

    //    @Scheduled(cron="0 0 0 25 12 ?") // it will run 25th December every year
    @Scheduled(cron = "0 0 0 1 1 ?") // it will run 1st January every year
    void createYearLimit(){
        List<TechnicalEquipmentSPRYearLimit> yearLimitList = technicalEquipmentSPRYearLimitRepository
                .getTechnicalEquipmentSPRYearLimitByYear(Long.parseLong("" + LocalDate.now().getYear()));

        List<TechnicalEquipmentSPRYearLimitDto> yearLimitDtoList = technicalEquipmentSPRYearLimitDtoMapper.toDtos(yearLimitList);

        yearLimitDtoList.forEach(yearLimitDto -> {
            technicalEquipmentSPRYearLimitService.save(TechnicalEquipmentSPRYearLimitDto.builder()
                    .year(yearLimitDto.getYear() + 1)
                    .count(yearLimitDto.getCount())
                    .volume(yearLimitDto.getVolume())
                    .fuelType(yearLimitDto.getFuelType())
                    .build());
        });
    }
}
