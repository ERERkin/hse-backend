package kz.ccecc.hse_backend.controller.batteryChargingController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingPollutionSourceDto;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingMothDataService;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingPollutionSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/battery-charging/pollution-sources")
@Api(value = "API for battery charging data on month",
        description = "API for battery charging data on month", produces = "application/json")
public class BatteryChargingMothDataController {
    @Autowired
    BatteryChargingMothDataService batteryChargingMothDataService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@ApiParam(name = "id",
            value = "Battery charging pollution source's id",
            required = true)
                                     @PathVariable("id") Long id){
        return ResponseEntity.ok(batteryChargingMothDataService.getById(id));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody BatteryChargingMothDataDto dataDto){
        return ResponseEntity.ok(batteryChargingMothDataService.save(dataDto));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(batteryChargingMothDataService.getAll());
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@ApiParam(name = "id",
            value = "Battery charging pollution source's id",
            required = true)
                                        @PathVariable("id") Long id){
        batteryChargingMothDataService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
