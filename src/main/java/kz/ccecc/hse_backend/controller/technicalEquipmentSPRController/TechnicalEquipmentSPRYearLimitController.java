package kz.ccecc.hse_backend.controller.technicalEquipmentSPRController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRYearLimitDto;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRYearLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/technical-equipment-spr/year-limits")
@Api(value = "API for technical equipment SPR year limit",
        description = "API for technical equipment SPR year limit", produces = "application/json")
public class TechnicalEquipmentSPRYearLimitController {
    @Autowired
    TechnicalEquipmentSPRYearLimitService technicalEquipmentSPRYearLimitService;

    @ApiOperation(value = "API for getting year limit by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(technicalEquipmentSPRYearLimitService.getById(id));
    }

    @ApiOperation(value = "API for creating and updating year limit")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody TechnicalEquipmentSPRYearLimitDto yearLimitDto){
        return ResponseEntity.ok(technicalEquipmentSPRYearLimitService.save(yearLimitDto));
    }

    @ApiOperation(value = "API for getting all year limits")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(technicalEquipmentSPRYearLimitService.getAll());
    }

    @ApiOperation(value = "API for deleting year limit by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        technicalEquipmentSPRYearLimitService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
