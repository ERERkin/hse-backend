package kz.ccecc.hse_backend.controller.batteryChargingController;

import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingPollutionSourceDto;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingPollutionSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/battery-charging/pollution-sources")
public class BatteryChargingPollutionSourceController {
    @Autowired
    BatteryChargingPollutionSourceService batteryChargingPollutionSourceService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(batteryChargingPollutionSourceService.getById(id));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody BatteryChargingPollutionSourceDto pollutionSource){
        return ResponseEntity.ok(batteryChargingPollutionSourceService.save(pollutionSource));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(batteryChargingPollutionSourceService.getAll());
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        batteryChargingPollutionSourceService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
