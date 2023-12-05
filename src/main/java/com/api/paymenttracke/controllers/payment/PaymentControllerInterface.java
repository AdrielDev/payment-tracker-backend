package com.api.paymenttracke.controllers.payment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

// import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.paymenttracke.dto.payment.PaymentRequestDTO;
import com.api.paymenttracke.models.Payment;

@Api(tags = { "payments" }, value = "Payments")
@RequestMapping("/payments")
public interface PaymentControllerInterface {
    @ApiOperation(value = "Get Payment by ID", authorizations = @Authorization("Bearer"), httpMethod = "GET", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("/{id}")
    ResponseEntity<Payment> getPaymentById(
            @ApiParam(name = "id", type = "Long", value = "ID of Payment", required = true) @PathVariable Long id);

    @ApiOperation(value = "Create Payment", httpMethod = "POST", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping
    ResponseEntity<Payment> createPayment(
            @ApiParam(name = "payment", value = "Payment object to be created", required = true) @RequestBody PaymentRequestDTO payment);

    @ApiOperation(value = "Update Payment by ID", authorizations = @Authorization("Bearer"), httpMethod = "PUT", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PutMapping("/{id}")
    ResponseEntity<Payment> updatePayment(
            @ApiParam(name = "id", type = "Long", value = "ID of the Payment to be updated", required = true) @PathVariable Long id,
            @ApiParam(name = "payment", value = "Updated Payment object", required = true) @RequestBody PaymentRequestDTO payment);

    @ApiOperation(value = "Delete Payment by ID", authorizations = @Authorization("Bearer"), httpMethod = "DELETE")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePayment(
            @ApiParam(name = "id", type = "Long", value = "ID of the Payment to be deleted", required = true) @PathVariable Long id);

//     @ApiOperation(value = "Get All Payments", authorizations = @Authorization("Bearer"), httpMethod = "GET", produces = "application/json")
//     @ApiResponses({
//             @ApiResponse(code = 200, message = "OK")
//     })
//     @GetMapping
//     ResponseEntity<List<Payment>> getAllPayments();
}
