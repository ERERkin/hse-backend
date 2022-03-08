package kz.ccecc.hse_backend.sheduler;

import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingYearLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BatteryChargingSheduler {

    @Autowired
    BatteryChargingYearLimitService batteryChargingYearLimitService;

    @Scheduled(cron = "0 0 0 1 1 ?")
    void createYearLimit(){
                
    }
}
