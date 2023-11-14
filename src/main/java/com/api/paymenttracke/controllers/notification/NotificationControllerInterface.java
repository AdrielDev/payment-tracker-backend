package com.api.paymenttracke.controllers.notification;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.paymenttracke.models.Notification;

@Api(tags = { "notification" }, value = "Notification")
@RequestMapping("/notifications")
public interface NotificationControllerInterface {

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Notification by ID", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    ResponseEntity<Notification> getNotificationById(
            @ApiParam(name = "id", type = "Long", value = "ID of the Notification", required = true) Long id);

    @GetMapping("/all")
    @ApiOperation(value = "Get All Notifications", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    ResponseEntity<List<Notification>> getAllNotifications();

    @PostMapping
    @ApiOperation(value = "Create Notification", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    ResponseEntity<Notification> createNotification(
            @ApiParam(name = "notification", value = "Notification object to be created", required = true) Notification notification);

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Notification by ID", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    ResponseEntity<Notification> updateNotification(
            @ApiParam(name = "id", type = "Long", value = "ID of the Notification to be updated", required = true) Long id,
            @ApiParam(name = "notification", value = "Updated Notification object", required = true) Notification updatedNotification);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Notification by ID", httpMethod = "DELETE")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    ResponseEntity<Void> deleteNotification(
            @ApiParam(name = "id", type = "Long", value = "ID of the Notification to be deleted", required = true) Long id);
}
