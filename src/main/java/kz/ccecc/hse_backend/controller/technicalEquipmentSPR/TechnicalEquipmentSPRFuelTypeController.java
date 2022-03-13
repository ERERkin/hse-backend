package kz.ccecc.hse_backend.controller.technicalEquipmentSPR;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionFuelTypeDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRFuelTypeDto;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionFuelTypeService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRFuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/technical-equipment-spr/fuel-types")
@Api(value = "API for technical equipment SPR fuel type",
        description = "API for technical equipment SPR fuel type", produces = "application/json")
public class TechnicalEquipmentSPRFuelTypeController {
    @Autowired
    TechnicalEquipmentSPRFuelTypeService technicalEquipmentSPRFuelTypeService;

    @ApiOperation(value = "API for getting fuel type by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(technicalEquipmentSPRFuelTypeService.getById(id));
    }

    @ApiOperation(value = "API for creating and updating fuel type")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody TechnicalEquipmentSPRFuelTypeDto fuelTypeDto){
        return ResponseEntity.ok(technicalEquipmentSPRFuelTypeService.save(fuelTypeDto));
    }

    @ApiOperation(value = "API for getting all fuel types")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(technicalEquipmentSPRFuelTypeService.getAll());
    }

    @ApiOperation(value = "API for deleting fuel type by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        technicalEquipmentSPRFuelTypeService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
