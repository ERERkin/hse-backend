package kz.ccecc.hse_backend.controller.technicalEquipmentSPRController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRMothDataDto;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRMothDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/technical-equipment-spr/month-data")
@Api(value = "API for technical equipment SPR month data",
        description = "API for technical equipment SPR month data", produces = "application/json")
public class TechnicalEquipmentSPRMothDataController {
    @Autowired
    TechnicalEquipmentSPRMothDataService technicalEquipmentSPRMothDataService;

    @ApiOperation(value = "API for getting month data by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(technicalEquipmentSPRMothDataService.getById(id));
    }

    @ApiOperation(value = "API for creating and updating month data")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody TechnicalEquipmentSPRMothDataDto mothDataDto){
        return ResponseEntity.ok(technicalEquipmentSPRMothDataService.save(mothDataDto));
    }

    @ApiOperation(value = "API for getting all month data")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(technicalEquipmentSPRMothDataService.getAll());
    }

    @ApiOperation(value = "API for deleting month data by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        technicalEquipmentSPRMothDataService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
