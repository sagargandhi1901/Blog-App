package com.sagar.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import static com.sagar.blog.constants.LogMessages.*;


@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Integer  categoryId;

    @NotEmpty
    @Size(min = 3, message = CATEGORY_TITLE_VALIDATION)
    private String categoryTitle;

    @NotEmpty
    @Size(min = 10, message = CATEGORY_DESCRIPTION_VALIDATION)
    private String categoryDescription;
}
