package kz.ccecc.hse_backend.controller.batteryChargingController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.ccecc.hse_backend.dto.batteryChargingDto.BatteryChargingPollutionSourceDto;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingPollutionSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/battery-charging/pollution-sources")
@Api(value = "API for battery charging pollution source",
        description = "API for battery charging pollution source", produces = "application/json")
public class BatteryChargingPollutionSourceController {
    @Autowired
    BatteryChargingPollutionSourceService batteryChargingPollutionSourceService;

    @ApiOperation(value = "API for getting pollution source by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(batteryChargingPollutionSourceService.getById(id));
    }

    @ApiOperation(value = "API for creating and updating pollution source")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody BatteryChargingPollutionSourceDto pollutionSource){
        return ResponseEntity.ok(batteryChargingPollutionSourceService.save(pollutionSource));
    }

    @ApiOperation(value = "API for getting all pollution sources")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(batteryChargingPollutionSourceService.getAll());
    }

    @ApiOperation(value = "API for deleting pollution source by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@ApiParam(name = "id",
            value = "Battery charging pollution source's id",
            required = true)
            @PathVariable("id") Long id){
        batteryChargingPollutionSourceService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
