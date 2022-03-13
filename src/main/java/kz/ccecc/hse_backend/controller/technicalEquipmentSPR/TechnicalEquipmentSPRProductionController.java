package kz.ccecc.hse_backend.controller.technicalEquipmentSPR;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionFuelTypeDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRProductionDto;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionFuelTypeService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/technical-equipment-spr/productions")
@Api(value = "API for technical equipment SPR production",
        description = "API for technical equipment SPR production", produces = "application/json")
public class TechnicalEquipmentSPRProductionController {
    @Autowired
    TechnicalEquipmentSPRProductionService technicalEquipmentSPRProductionService;

    @ApiOperation(value = "API for getting production by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(technicalEquipmentSPRProductionService.getById(id));
    }

    @ApiOperation(value = "API for creating and updating production")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody TechnicalEquipmentSPRProductionDto productionDto){
        return ResponseEntity.ok(technicalEquipmentSPRProductionService.save(productionDto));
    }

    @ApiOperation(value = "API for getting all productions")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(technicalEquipmentSPRProductionService.getAll());
    }

    @ApiOperation(value = "API for deleting production by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        technicalEquipmentSPRProductionService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
