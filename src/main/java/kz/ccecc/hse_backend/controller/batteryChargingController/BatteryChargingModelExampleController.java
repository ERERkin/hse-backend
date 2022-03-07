package kz.ccecc.hse_backend.controller.batteryChargingController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.ccecc.hse_backend.service.batteryChargingService.BatteryChargingModelExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/battery-charging/model-example")
@Api(value = "API for model examples",
        description = "API for model examples", produces = "application/json")
public class BatteryChargingModelExampleController {
    @Autowired
    BatteryChargingModelExampleService batteryChargingModelExampleService;

    @ApiOperation(value = "API for getting production example version 1")
    @GetMapping("/production/v1")
    ResponseEntity<?> getProductionV1(){
        return ResponseEntity.ok(batteryChargingModelExampleService.getBatteryChargingProductionExampleV1());
    }

    @ApiOperation(value = "API for getting pollution source example version 1")
    @GetMapping("/pollution-source/v1")
    ResponseEntity<?> getPollutionSourceV1(){
        return ResponseEntity.ok(batteryChargingModelExampleService.getBatteryChargingPollutionSourceExampleV1());
    }

    @ApiOperation(value = "API for getting year limit example version 1")
    @GetMapping("/year-limit/v1")
    ResponseEntity<?> getYearLimitV1(){
        return ResponseEntity.ok(batteryChargingModelExampleService.getBatteryChargingYearLimitExampleV1());
    }

    @ApiOperation(value = "API for getting month data example version 1")
    @GetMapping("/month-data/v1")
    ResponseEntity<?> getMothDataV1(){
        return ResponseEntity.ok(batteryChargingModelExampleService.getBatteryChargingMothDataExampleV1());
    }
}
