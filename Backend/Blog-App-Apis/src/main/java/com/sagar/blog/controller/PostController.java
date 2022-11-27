package com.sagar.blog.controller;

import com.sagar.blog.payload.ApiResponse;
import com.sagar.blog.payload.PostDTO;
import com.sagar.blog.payload.UserDTO;
import com.sagar.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sagar.blog.constants.ApiConstant.CATEGORY_DELETE_MESSAGE;
import static com.sagar.blog.constants.ApiConstant.POST_DELETE_MESSAGE;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDTO createdPostDto = this.service.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDTO> postsByUser = this.service.getPostsByUser(userId);
        return new ResponseEntity<>(postsByUser, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId) {
        List<PostDTO> postsByCategory = this.service.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postsByCategory, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts= this.service.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
        PostDTO postDTO = this.service.getPostById(postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        this.service.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse(POST_DELETE_MESSAGE, true), HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,
                                              @PathVariable Integer postId) {
        PostDTO updatedPostDTO = this.service.updatePost(postDTO, postId);
        return new ResponseEntity<>(updatedPostDTO, HttpStatus.OK);
    }
}
