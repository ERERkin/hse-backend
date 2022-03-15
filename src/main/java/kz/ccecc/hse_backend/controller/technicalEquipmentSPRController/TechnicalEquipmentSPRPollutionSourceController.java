package kz.ccecc.hse_backend.controller.technicalEquipmentSPRController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRPollutionSourceDto;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRPollutionSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/technical-equipment-spr/pollution-sources")
@Api(value = "API for technical equipment SPR pollution source",
        description = "API for technical equipment SPR pollution source", produces = "application/json")
public class TechnicalEquipmentSPRPollutionSourceController {
    @Autowired
    TechnicalEquipmentSPRPollutionSourceService technicalEquipmentSPRPollutionSourceService;

    @ApiOperation(value = "API for getting pollution source by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(technicalEquipmentSPRPollutionSourceService.getById(id));
    }

    @ApiOperation(value = "API for creating and updating pollution source")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody TechnicalEquipmentSPRPollutionSourceDto pollutionSourceDto){
        return ResponseEntity.ok(technicalEquipmentSPRPollutionSourceService.save(pollutionSourceDto));
    }

    @ApiOperation(value = "API for getting all pollution sources")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(technicalEquipmentSPRPollutionSourceService.getAll());
    }

    @ApiOperation(value = "API for deleting pollution source by id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        technicalEquipmentSPRPollutionSourceService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
