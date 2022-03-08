package kz.ccecc.hse_backend.controller.fuelCombustion;

import io.swagger.annotations.ApiOperation;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingYearLimitDto;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionYearLimitDto;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingYearLimitService;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionYearLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/fuel-combustion/year-limits")
public class FuelCombustionYearLimitController {
    @Autowired
    FuelCombustionYearLimitService fuelCombustionYearLimitService;

    @ApiOperation(value = "API for getting year limit by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(fuelCombustionYearLimitService.getById(id));
    }

    @ApiOperation(value = "API for creating and updating year limit")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody FuelCombustionYearLimitDto yearLimitDto){
        return ResponseEntity.ok(fuelCombustionYearLimitService.save(yearLimitDto));
    }

    @ApiOperation(value = "API for getting all year limits")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(fuelCombustionYearLimitService.getAll());
    }

    @ApiOperation(value = "API for deleting year limit by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        fuelCombustionYearLimitService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
