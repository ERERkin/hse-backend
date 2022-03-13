package kz.ccecc.hse_backend.controller.fuelCombustionController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionFuelTypeDto;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionFuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/fuel-combustion/fuel-types")
@Api(value = "API for fuel combustion fuel type",
        description = "API for fuel combustion fuel type", produces = "application/json")
public class FuelCombustionFuelTypeController {
    @Autowired
    FuelCombustionFuelTypeService fuelCombustionFuelTypeService;

    @ApiOperation(value = "API for getting fuel type by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(fuelCombustionFuelTypeService.getById(id));
    }

    @ApiOperation(value = "API for creating and updating fuel type")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody FuelCombustionFuelTypeDto fuelTypeDto){
        return ResponseEntity.ok(fuelCombustionFuelTypeService.save(fuelTypeDto));
    }

    @ApiOperation(value = "API for getting all fuel types")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(fuelCombustionFuelTypeService.getAll());
    }

    @ApiOperation(value = "API for deleting fuel type by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        fuelCombustionFuelTypeService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
