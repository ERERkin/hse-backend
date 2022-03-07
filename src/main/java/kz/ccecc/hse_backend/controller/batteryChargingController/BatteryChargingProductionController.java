package kz.ccecc.hse_backend.controller.batteryChargingController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingProductionDto;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/battery-charging/productions")
public class BatteryChargingProductionController {
    @Autowired
    BatteryChargingProductionService batteryChargingProductionService;

    @ApiOperation(value = "API for getting production by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(batteryChargingProductionService.getById(id));
    }

    @ApiOperation(value = "API for creating and updating production")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@ApiParam(value = "When we create or update production and production's dependencies")
                                      @RequestBody BatteryChargingProductionDto production){
        return ResponseEntity.ok(batteryChargingProductionService.save(production));
    }

    @ApiOperation(value = "API for getting all productions")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(batteryChargingProductionService.getAll());
    }

    @ApiOperation(value = "API for deleting production by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        batteryChargingProductionService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
