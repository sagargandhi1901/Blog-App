package com.sagar.blog.service;

import com.sagar.blog.model.Post;
import com.sagar.blog.payload.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    Post updatePost(PostDTO postDTO, Integer postId);

    void deletePost(Integer postId);

    List<Post> getAllPosts();

    Post getPostById(Integer postId);

    List<Post> getPostsByCategory(Integer categoryId);

    List<Post> getPostsByUser(Integer userId);

    List<Post> searchPostsByKeyword(String keyword);
}
