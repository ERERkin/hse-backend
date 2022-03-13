package kz.ccecc.hse_backend.controller.fuelCombustionController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionPollutionSourceDto;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionPollutionSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/fuel-combustion/pollution-sources")
@Api(value = "API for fuel combustion pollution source",
        description = "API for fuel combustion  pollution source", produces = "application/json")
public class FuelCombustionPollutionSourceController {
    @Autowired
    FuelCombustionPollutionSourceService fuelCombustionPollutionSourceService;

    @ApiOperation(value = "API for getting pollution source by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(fuelCombustionPollutionSourceService.getById(id));
    }

    @ApiOperation(value = "API for creating and updating pollution source")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody FuelCombustionPollutionSourceDto pollutionSource){
        return ResponseEntity.ok(fuelCombustionPollutionSourceService.save(pollutionSource));
    }

    @ApiOperation(value = "API for getting all pollution sources")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(fuelCombustionPollutionSourceService.getAll());
    }

    @ApiOperation(value = "API for deleting pollution source by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        fuelCombustionPollutionSourceService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
