package com.api.paymenttracke.controllers.recurringpayment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.paymenttracke.models.RecurringPayment;

@Api(tags = { "recurring-payments" }, value = "Recurring Payments")
@RequestMapping("/recurring-payments")
public interface RecurringPaymentControllerInterface {

        @GetMapping("/{id}")
        @ApiOperation(value = "Get Recurring Payment by ID", authorizations = @Authorization("Bearer"), httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses({
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 404, message = "Not Found")
        })
        ResponseEntity<RecurringPayment> getRecurringPaymentById(
                        @ApiParam(name = "id", type = "Long", value = "ID of Recurring Payment", required = true) final Long id);

        @GetMapping("/all")
        @ApiOperation(value = "Get all Payment Recurring", authorizations = @Authorization("Bearer"), httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses({
                        @ApiResponse(code = 200, message = "OK")
        })
        ResponseEntity<List<RecurringPayment>> getAllRecurringPayment();

        @PostMapping
        ResponseEntity<RecurringPayment> createRecurringPayment(
                        @ApiParam(name = "recurringPayment", value = "Recurring Payment object to be created", required = true) @RequestBody RecurringPayment recurringPayment);

        @ApiOperation(value = "Update Recurring Payment by ID", authorizations = @Authorization("Bearer"), httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses({
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 404, message = "Not Found")
        })
        @PutMapping("/{id}")
        ResponseEntity<RecurringPayment> updateRecurringPayment(
                        @ApiParam(name = "id", type = "Long", value = "ID of the Recurring Payment to be updated", required = true) @PathVariable Long id,
                        @ApiParam(name = "recurringPayment", value = "Updated Recurring Payment object", required = true) @RequestBody RecurringPayment recurringPayment);

        @ApiOperation(value = "Partial Update Recurring Payment by ID", authorizations = @Authorization("Bearer"), httpMethod = "PATCH", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses({
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 404, message = "Not Found")
        })
        @PatchMapping("/{id}")
        ResponseEntity<RecurringPayment> partialUpdateRecurringPayment(
                        @ApiParam(name = "id", type = "Long", value = "ID of the Recurring Payment to be partially updated", required = true) @PathVariable Long id,
                        @ApiParam(name = "recurringPayment", value = "Partial updates for the Recurring Payment", required = true) @RequestBody RecurringPayment recurringPayment);

        @ApiOperation(value = "Delete Recurring Payment by ID", authorizations = @Authorization("Bearer"), httpMethod = "DELETE")
        @ApiResponses({
                        @ApiResponse(code = 204, message = "No Content"),
                        @ApiResponse(code = 404, message = "Not Found")
        })
        @DeleteMapping("/{id}")
        ResponseEntity<Void> deleteRecurringPayment(
                        @ApiParam(name = "id", type = "Long", value = "ID of the Recurring Payment to be deleted", required = true) @PathVariable Long id);
}