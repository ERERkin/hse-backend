package kz.ccecc.hse_backend.controller.batteryChargingController;

import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "API for getting year limit by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(batteryChargingYearLimitService.getById(id));
    }

    @ApiOperation(value = "API for creating and updating year limit")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody BatteryChargingYearLimitDto batteryChargingYearLimitDto){
        return ResponseEntity.ok(batteryChargingYearLimitService.save(batteryChargingYearLimitDto));
    }

    @ApiOperation(value = "API for getting all year limits")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(batteryChargingYearLimitService.getAll());
    }

    @ApiOperation(value = "API for deleting year limit by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        batteryChargingYearLimitService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
