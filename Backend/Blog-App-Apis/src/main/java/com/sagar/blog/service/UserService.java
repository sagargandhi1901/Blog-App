package com.sagar.blog.service;

import com.sagar.blog.payload.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDto);

    UserDTO updateUser(UserDTO userDto, Integer userId);

    UserDTO getUserById(Integer userId);

    List<UserDTO> getAllUsers();

    void deleteUser(Integer userId);
}
