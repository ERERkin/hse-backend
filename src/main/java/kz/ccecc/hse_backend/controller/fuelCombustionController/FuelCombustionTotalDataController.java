package kz.ccecc.hse_backend.controller.fuelCombustionController;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/fuel-combustion/year-limits")
@Api(value = "API for getting report totals",
        description = "API for fuel combustion fuel type", produces = "application/json")
public class FuelCombustionTotalDataController {
    ResponseEntity<?> getTotalFuelCombustion(@RequestParam Long year){
        return ResponseEntity.ok("Success");
    }
}
