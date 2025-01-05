package com.sjitzooi.templatelibrary_sql.entity.TemplateParts;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TemplatePostInput {

    @NotEmpty
    @Size(min = 1, max = 80, message = "Title must be within 1 to 80 characters")
    private String title;

    @NotEmpty
    @Size(min = 0, max = 500 ,message = "Description must be between 0 and 500 characters")
    private String description;

    @PastOrPresent
    private LocalDate createdDate;

    @NotEmpty
    private String authorId;
}
