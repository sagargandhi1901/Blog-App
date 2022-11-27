package com.sagar.blog.service;

import com.sagar.blog.model.Post;
import com.sagar.blog.payload.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    PostDTO updatePost(PostDTO postDTO, Integer postId);

    void deletePost(Integer postId);

    List<PostDTO> getAllPosts();

    PostDTO getPostById(Integer postId);

    List<PostDTO> getPostsByCategory(Integer categoryId);

    List<PostDTO> getPostsByUser(Integer userId);

    List<PostDTO> searchPostsByKeyword(String keyword);
}
