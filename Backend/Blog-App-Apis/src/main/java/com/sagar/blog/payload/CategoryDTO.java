package com.sagar.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    @NotEmpty
    private Integer  categoryId;
    private String categoryTitle;
    private String categoryDescription;
}
