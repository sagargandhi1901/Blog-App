package com.sagar.blog.payload;

import com.sagar.blog.model.Category;
import com.sagar.blog.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
    private Integer postId;

    private String postTitle;

    private String postContent;

    private String imageName;

    private Date addedDate;

    private CategoryDTO category;

    private UserDTO user;
}
