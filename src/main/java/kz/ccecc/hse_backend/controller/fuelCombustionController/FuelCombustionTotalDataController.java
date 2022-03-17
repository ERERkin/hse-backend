package kz.ccecc.hse_backend.controller.fuelCombustionController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/fuel-combustion/total")
@Api(value = "API for getting report totals",
        description = "API for getting report totals", produces = "application/json")
public class FuelCombustionTotalDataController {
    @Autowired
    FuelCombustionTotalService fuelCombustionTotalService;

    @ApiOperation(value = "API for getting report totals")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping()
    ResponseEntity<?> getTotalFuelCombustion(@RequestParam Long year){
        return ResponseEntity.ok(fuelCombustionTotalService.getFuelCombustionTotal(year));
    }
}
