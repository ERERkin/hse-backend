package kz.ccecc.hse_backend.controller.fuelCombustionController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionMothDataDto;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionMothDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/fuel-combustion/month-data")
@Api(value = "API for fuel combustion data on month",
        description = "API for fuel combustion data on month", produces = "application/json")
public class FuelCombustionMothDataController {
    @Autowired
    FuelCombustionMothDataService fuelCombustionMothDataService;

    @ApiOperation(value = "API for getting month data by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@ApiParam(name = "id",
            value = "Fuel combustion month data's id",
            required = true) @PathVariable("id") Long id) {
        return ResponseEntity.ok(fuelCombustionMothDataService.getById(id));
    }

    @ApiOperation(value = "API for creating and updating month data")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody FuelCombustionMothDataDto dataDto) {
        return ResponseEntity.ok(fuelCombustionMothDataService.save(dataDto));
    }

    @ApiOperation(value = "API for getting all month data")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(fuelCombustionMothDataService.getAll());
    }

    @ApiOperation(value = "API for deleting month data by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        fuelCombustionMothDataService.deleteById(id);
        return ResponseEntity.ok("Success");
    }

//    @ApiOperation(value = "API for getting quarter data by year data and quarter's number")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    @GetMapping("/quarter")
//    public ResponseEntity<?> getQuarterDateByYearAndQuarterNum(@RequestParam("yearLimitId") Long yearLimitId,
//                                                               @RequestParam("year") Long year,
//                                                               @RequestParam("num") Long num) {
//        return ResponseEntity.ok(fuelCombustionMothDataService.getQuarterDateByYearAndQuarterNum(yearLimitId, year, num));
//    }
}
