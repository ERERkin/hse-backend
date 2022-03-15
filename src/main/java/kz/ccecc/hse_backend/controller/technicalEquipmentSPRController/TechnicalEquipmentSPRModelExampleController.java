package kz.ccecc.hse_backend.controller.technicalEquipmentSPRController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRModelExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/technical-equipment-spr/model-example")
@Api(value = "API for model examples",
        description = "API for model examples", produces = "application/json")
public class TechnicalEquipmentSPRModelExampleController {
    @Autowired
    TechnicalEquipmentSPRModelExampleService technicalEquipmentSPRModelExampleService;

    @ApiOperation(value = "API for getting production example version 1")
    @GetMapping("/production/v1")
    ResponseEntity<?> getTechnicalEquipmentSPRProductionExampleV1(){
        return ResponseEntity.ok(technicalEquipmentSPRModelExampleService.getTechnicalEquipmentSPRProductionExampleV1());
    }

    @ApiOperation(value = "API for getting production example version 2")
    @GetMapping("/production/v2")
    ResponseEntity<?> getTechnicalEquipmentSPRProductionExampleV2(){
        return ResponseEntity.ok(technicalEquipmentSPRModelExampleService.getTechnicalEquipmentSPRProductionExampleV2());
    }

    @ApiOperation(value = "API for getting pollution source example version 1")
    @GetMapping("/pollution-source/v1")
    ResponseEntity<?> getTechnicalEquipmentSPRPollutionSourceExampleV1(){
        return ResponseEntity.ok(technicalEquipmentSPRModelExampleService.getTechnicalEquipmentSPRPollutionSourceExampleV1());
    }

    @ApiOperation(value = "API for getting pollution source example version 2")
    @GetMapping("/pollution-source/v2")
    ResponseEntity<?> getTechnicalEquipmentSPRPollutionSourceExampleV2(){
        return ResponseEntity.ok(technicalEquipmentSPRModelExampleService.getTechnicalEquipmentSPRPollutionSourceExampleV1());
    }

    @ApiOperation(value = "API for getting fuel type example version 1")
    @GetMapping("/fuel-type/v1")
    ResponseEntity<?> getTechnicalEquipmentSPRFuelTypeExampleV1(){
        return ResponseEntity.ok(technicalEquipmentSPRModelExampleService.getTechnicalEquipmentSPRFuelTypeExampleV1());
    }

    @ApiOperation(value = "API for getting fuel type example version 2")
    @GetMapping("/fuel-type/v2")
    ResponseEntity<?> getTechnicalEquipmentSPRFuelTypeExampleV2(){
        return ResponseEntity.ok(technicalEquipmentSPRModelExampleService.getTechnicalEquipmentSPRFuelTypeExampleV2());
    }

    @ApiOperation(value = "API for getting year limit example version 1")
    @GetMapping("/year-limit/v1")
    ResponseEntity<?> getTechnicalEquipmentSPRYearLimitExampleV1(){
        return ResponseEntity.ok(technicalEquipmentSPRModelExampleService.getTechnicalEquipmentSPRYearLimitExampleV1());
    }

    @ApiOperation(value = "API for getting year limit example version 2")
    @GetMapping("/year-limit/v2")
    ResponseEntity<?> getTechnicalEquipmentSPRYearLimitExampleV2(){
        return ResponseEntity.ok(technicalEquipmentSPRModelExampleService.getTechnicalEquipmentSPRYearLimitExampleV2());
    }

    @ApiOperation(value = "API for getting month data example version 1")
    @GetMapping("/month-data/v1")
    ResponseEntity<?> getTechnicalEquipmentSPRMothDataExampleV1(){
        return ResponseEntity.ok(technicalEquipmentSPRModelExampleService.getTechnicalEquipmentSPRMothDataExampleV1());
    }

    @ApiOperation(value = "API for getting month data example version 2")
    @GetMapping("/month-data/v2")
    ResponseEntity<?> getTechnicalEquipmentSPRMothDataExampleV2(){
        return ResponseEntity.ok(technicalEquipmentSPRModelExampleService.getTechnicalEquipmentSPRMothDataExampleV2());
    }
}
