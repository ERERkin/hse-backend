package kz.ccecc.hse_backend.controller.fuelCombustionController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionProductionDto;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/fuel-combustion/productions")
@Api(value = "API for fuel combustion production",
        description = "API for fuel combustion production", produces = "application/json")
public class FuelCombustionProductionController {
    @Autowired
    FuelCombustionProductionService fuelCombustionProductionService;

    @ApiOperation(value = "API for getting production by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(fuelCombustionProductionService.getById(id));
    }

    @ApiOperation(value = "API for creating and updating production")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@ApiParam(value = "When we create or update production and production's dependencies")
                                      @RequestBody FuelCombustionProductionDto production){
        return ResponseEntity.ok(fuelCombustionProductionService.save(production));
    }

    @ApiOperation(value = "API for getting all productions")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(fuelCombustionProductionService.getAll());
    }

    @ApiOperation(value = "API for deleting production by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        fuelCombustionProductionService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
