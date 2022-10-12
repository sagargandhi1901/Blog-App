package com.sagar.blog.controller;

import com.sagar.blog.payload.ApiResponse;
import com.sagar.blog.payload.UserDTO;
import com.sagar.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.sagar.blog.constants.ApiConstant.USER_DELETE_MESSAGE;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUserDto = this.service.createUser(userDTO);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,
                                              @PathVariable Integer userId) {
        UserDTO updatedUser = this.service.updateUser(userDTO, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(this.service.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.service.getUserById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        this.service.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse(USER_DELETE_MESSAGE, true), HttpStatus.OK);
    }
}
