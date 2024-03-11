package com.pee.dockerized.postgresql.controller;

import com.pee.dockerized.postgresql.entity.User;
import com.pee.dockerized.postgresql.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Enpoint", description = "Easy Endpoint for docker project")
@RequiredArgsConstructor
public class UserController {
    private final UserService userServiceImpl;

    @GetMapping
    @Operation(method = "GET", summary = "Get endpoint for get all users")
    @ApiResponse(responseCode = "200", description = "List all users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> list = userServiceImpl.getAllUsers();
        return list != null ? new ResponseEntity<List<User>>(list, HttpStatus.OK)
        : new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
    }
    @Operation(method = "GET", summary = "Get endpoint for get an id users")
    @ApiResponse(responseCode = "200", description = "Get a user")
    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User userRec = userServiceImpl.getUserById(id);
        return userRec != null ? new ResponseEntity<User>(userRec, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
