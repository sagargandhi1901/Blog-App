package com.sagar.blog.service.impl;

import com.sagar.blog.exception.ResourceNotFoundException;
import com.sagar.blog.model.User;
import com.sagar.blog.payload.UserDTO;
import com.sagar.blog.repository.UserRepository;
import com.sagar.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = this.dtoToEntity(userDto);
        User savedUser = this.repository.save(user);
        return this.entityToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Integer userId) {
        User user = this.repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.repository.save(user);
        return this.entityToDto(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = this.repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return this.entityToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.repository.findAll();
        List<UserDTO> userDtos = users.stream().map(user -> this.entityToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.repository.delete(user);
    }

    private User dtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        return user;
    }

    private UserDTO entityToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAbout(user.getAbout());

        return userDTO;
    }
}
