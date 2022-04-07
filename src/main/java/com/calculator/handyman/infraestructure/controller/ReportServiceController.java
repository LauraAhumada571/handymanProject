package com.calculator.handyman.infraestructure.controller;

import com.calculator.handyman.infraestructure.controller.models.ReportServiceDTO;
import com.calculator.handyman.infraestructure.controller.models.ReportServiceInput;
import com.calculator.handyman.infraestructure.controller.services.ReportServices;
import com.calculator.handyman.shared.errors.ApplicationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ReportServiceController {
    private final ReportServices reportServices;

    public ReportServiceController(ReportServices reportServices) {
        this.reportServices = reportServices;
    }

    @RequestMapping(value = "/reportservice", method = RequestMethod.POST)
    public ResponseEntity<?> createReportService(
            @RequestBody ReportServiceInput reportServiceInput
    ){
        try{
            ReportServiceDTO reportServiceDTO = reportServices.storeReportService(reportServiceInput);
            return ResponseEntity.ok(reportServiceDTO);
        } catch (IllegalArgumentException | NullPointerException e){
            ApplicationError error = new ApplicationError(
                    "InputDataValidationError",
                    "Bad input data",
                    Map.of(
                            "error", e.getMessage()
                    )
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        } catch (Exception e){
            ApplicationError error = new ApplicationError(
                    "SystemError",
                    e.getMessage(),
                    Map.of()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }

    @RequestMapping(value = "/reportservices/{technicianId}/{numberOfWeek}", method = RequestMethod.GET)
    public ResponseEntity<Object> listReportServiceWeek(
            @PathVariable("technicianId") String technicianId,
            @PathVariable("numberOfWeek") int numberOfWeek
    ) {
        try {
            return ResponseEntity.ok(reportServices.getListReportServiceWeek(technicianId, numberOfWeek));
        } catch (Exception e) {
            ApplicationError error = new ApplicationError(
                    "SystemError",
                    e.getMessage(),
                    Map.of()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }
}
