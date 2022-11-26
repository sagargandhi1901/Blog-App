package com.sagar.blog.repository;

import com.sagar.blog.model.Category;
import com.sagar.blog.model.Post;
import com.sagar.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);
}
