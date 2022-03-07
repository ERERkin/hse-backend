package kz.ccecc.hse_backend.controller.batteryChargingController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingMothDataDto;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingMothDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/battery-charging/month-data")
@Api(value = "API for battery charging data on month",
        description = "API for battery charging data on month", produces = "application/json")
public class BatteryChargingMothDataController {
    @Autowired
    BatteryChargingMothDataService batteryChargingMothDataService;

    @ApiOperation(value = "API for getting month data by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@ApiParam(name = "id",
            value = "Battery charging month data's id",
            required = true) @PathVariable("id") Long id) {
        return ResponseEntity.ok(batteryChargingMothDataService.getById(id));
    }

    @ApiOperation(value = "API for creating and updating month data")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody BatteryChargingMothDataDto dataDto) {
        return ResponseEntity.ok(batteryChargingMothDataService.save(dataDto));
    }

    @ApiOperation(value = "API for getting all month data")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(batteryChargingMothDataService.getAll());
    }

    @ApiOperation(value = "API for deleting month data by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@ApiParam(name = "id",
            value = "Battery charging month data's id",
            required = true)
                                        @PathVariable("id") Long id) {
        batteryChargingMothDataService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
