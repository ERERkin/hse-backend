package kz.ccecc.hse_backend.controller.fuelCombustion;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.ccecc.hse_backend.service.fuelCombustionService.FuelCombustionModelExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/fuel-combustion/model-example")
@Api(value = "API for model examples",
        description = "API for model examples", produces = "application/json")
public class FuelCombustionModelExampleController {
    @Autowired
    FuelCombustionModelExampleService fuelCombustionModelExampleService;


    @ApiOperation(value = "API for getting production example version 1")
    @GetMapping("/production/v1")
    ResponseEntity<?> getProductionV1(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionProductionExampleV1());
    }

    @ApiOperation(value = "API for getting production example version 2")
    @GetMapping("/production/v2")
    ResponseEntity<?> getProductionV2(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionProductionExampleV2());
    }

    @ApiOperation(value = "API for getting production example version 3")
    @GetMapping("/production/v3")
    ResponseEntity<?> getProductionV3(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionProductionExampleV3());
    }

    @ApiOperation(value = "API for getting pollution source example version 1")
    @GetMapping("/pollution-source/v1")
    ResponseEntity<?> getPollutionSourceV1(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionPollutionSourceExampleV1());
    }

    @ApiOperation(value = "API for getting pollution source example version 2")
    @GetMapping("/pollution-source/v2")
    ResponseEntity<?> getPollutionSourceV2(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionPollutionSourceExampleV2());
    }

    @ApiOperation(value = "API for getting pollution source example version 3")
    @GetMapping("/pollution-source/v3")
    ResponseEntity<?> getPollutionSourceV3(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionPollutionSourceExampleV3());
    }

    @ApiOperation(value = "API for getting fuel type example version 1")
    @GetMapping("/fuel-type/v1")
    ResponseEntity<?> getFuelTypeV1(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionFuelTypeExampleV1());
    }

    @ApiOperation(value = "API for getting fuel type example version 2")
    @GetMapping("/fuel-type/v2")
    ResponseEntity<?> getFuelTypeV2(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionFuelTypeExampleV2());
    }

    @ApiOperation(value = "API for getting fuel type example version 3")
    @GetMapping("/fuel-type/v3")
    ResponseEntity<?> getFuelTypeV3(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionFuelTypeExampleV3());
    }

    @ApiOperation(value = "API for getting year limit example version 1")
    @GetMapping("/year-limit/v1")
    ResponseEntity<?> getYearLimitV1(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionFuelTypeExampleV1());
    }

    @ApiOperation(value = "API for getting year limit example version 2")
    @GetMapping("/year-limit/v2")
    ResponseEntity<?> getYearLimitV2(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionFuelTypeExampleV2());
    }

    @ApiOperation(value = "API for getting year limit example version 3")
    @GetMapping("/year-limit/v3")
    ResponseEntity<?> getYearLimitV3(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionFuelTypeExampleV3());
    }

    @ApiOperation(value = "API for getting month data example version 1")
    @GetMapping("/month-data/v1")
    ResponseEntity<?> getMothDataV1(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionMothDataExampleV1());
    }

    @ApiOperation(value = "API for getting month data example version 2")
    @GetMapping("/month-data/v2")
    ResponseEntity<?> getMothDataV2(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionMothDataExampleV2());
    }

    @ApiOperation(value = "API for getting month data example version 3")
    @GetMapping("/month-data/v3")
    ResponseEntity<?> getMothDataV3(){
        return ResponseEntity.ok(fuelCombustionModelExampleService.getFuelCombustionMothDataExampleV3());
    }
}
