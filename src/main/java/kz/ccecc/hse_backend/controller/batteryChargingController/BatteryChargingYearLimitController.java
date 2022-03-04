package kz.ccecc.hse_backend.controller.batteryChargingController;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearLimitDto;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingYearLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/battery-charging/year-limits")
public class BatteryChargingYearLimitController {
    @Autowired
    BatteryChargingYearLimitService batteryChargingYearLimitService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(batteryChargingYearLimitService.getById(id));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody BatteryChargingYearLimitDto batteryChargingYearLimitDto){
        return ResponseEntity.ok(batteryChargingYearLimitService.save(batteryChargingYearLimitDto));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(batteryChargingYearLimitService.getAll());
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        batteryChargingYearLimitService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
