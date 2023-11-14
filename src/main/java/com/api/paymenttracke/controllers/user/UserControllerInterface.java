package com.api.paymenttracke.controllers.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.paymenttracke.models.User;

@Api(tags = { "user" }, value = "User Final")
@RequestMapping("/users")
public interface UserControllerInterface {

        @GetMapping("/{id}")
        @ApiOperation(value = "Get User by id", authorizations = @Authorization("Bearer"), httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses({
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 404, message = "Not Found")
        })
        ResponseEntity<User> getUserById(
                        @ApiParam(name = "id", type = "Long", value = "id of User", required = true) final Long id);

        @PostMapping
        ResponseEntity<User> createUser(
                        @ApiParam(name = "user", value = "User object to be created", required = true) @RequestBody User user);

        @ApiOperation(value = "Update User by id", authorizations = @Authorization("Bearer"), httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses({
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 404, message = "Not Found")
        })
        @PutMapping("/{id}")
        ResponseEntity<User> updateUser(
                        @ApiParam(name = "id", type = "Long", value = "ID of the User to be updated", required = true) @PathVariable Long id,
                        @ApiParam(name = "user", value = "Updated User object", required = true) @RequestBody User user);

        @ApiOperation(value = "Partial Update User by id", authorizations = @Authorization("Bearer"), httpMethod = "PATCH", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses({
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 404, message = "Not Found")
        })
        @PatchMapping("/{id}")
        ResponseEntity<User> partialUpdateUser(
                        @ApiParam(name = "id", type = "Long", value = "ID of the User to be partially updated", required = true) @PathVariable Long id,
                        @ApiParam(name = "user", value = "Partial updates for the User", required = true) @RequestBody User user);

        @ApiOperation(value = "Delete User by id", authorizations = @Authorization("Bearer"), httpMethod = "DELETE")
        @ApiResponses({
                        @ApiResponse(code = 204, message = "No Content"),
                        @ApiResponse(code = 404, message = "Not Found")
        })
        @DeleteMapping("/{id}")
        ResponseEntity<Void> deleteUser(
                        @ApiParam(name = "id", type = "Long", value = "ID of the User to be deleted", required = true) @PathVariable Long id);
}
