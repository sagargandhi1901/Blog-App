package com.sagar.blog.service.impl;

import com.sagar.blog.exception.ResourceNotFoundException;
import com.sagar.blog.model.Category;
import com.sagar.blog.model.Post;
import com.sagar.blog.model.User;
import com.sagar.blog.payload.CategoryDTO;
import com.sagar.blog.payload.PostDTO;
import com.sagar.blog.repository.CategoryRepository;
import com.sagar.blog.repository.PostRepository;
import com.sagar.blog.repository.UserRepository;
import com.sagar.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.sagar.blog.constants.ApiConstant.*;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(USER, ID, userId));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, categoryId));

        Post post = this.mapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost = this.postRepository.save(post);

        return this.mapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post retrievedPost = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(POST, ID, postId));
        retrievedPost.setPostTitle(postDTO.getPostTitle());
        retrievedPost.setPostContent(postDTO.getPostContent());
        retrievedPost.setImageName(postDTO.getImageName());
        Post savedPost = this.postRepository.save(retrievedPost);

        return this.mapper.map(savedPost, PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post retrievedPost = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(POST, ID, postId));
        this.postRepository.delete(retrievedPost);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = this.postRepository.findAll();
        return posts.stream().map((post) -> this.mapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post retrievedPost = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(POST, ID, postId));
        return this.mapper.map(retrievedPost, PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {
        Category retrievedCategory = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, categoryId));
        List<Post> posts = this.postRepository.findByCategory(retrievedCategory);

        return posts.stream().map((post) -> this.mapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {
        User retrievedUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(USER, ID, userId));
        List<Post> posts = this.postRepository.findByUser(retrievedUser);

        return posts.stream().map((post) -> this.mapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> searchPostsByKeyword(String keyword) {
        return null;
    }
}
