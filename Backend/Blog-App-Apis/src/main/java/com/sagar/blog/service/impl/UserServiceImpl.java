package com.sagar.blog.service.impl;

import com.sagar.blog.exception.ResourceNotFoundException;
import com.sagar.blog.model.User;
import com.sagar.blog.payload.UserDTO;
import com.sagar.blog.repository.UserRepository;
import com.sagar.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.sagar.blog.constants.ApiConstant.USER;
import static com.sagar.blog.constants.ApiConstant.ID;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = this.dtoToEntity(userDto);
        User savedUser = this.repository.save(user);
        return this.entityToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Integer userId) {
        User user = this.repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(USER, ID, userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.repository.save(user);
        return this.entityToDto(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = this.repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(USER, ID, userId));
        return this.entityToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.repository.findAll();
        return users.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(USER, ID, userId));
        this.repository.delete(user);
    }

    private User dtoToEntity(UserDTO userDTO) {
        return this.modelMapper.map(userDTO, User.class);
    }

    private UserDTO entityToDto(User user) {
        return this.modelMapper.map(user, UserDTO.class);
    }
}
