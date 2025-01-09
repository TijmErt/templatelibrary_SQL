package com.sjitzooi.templatelibrary_sql.entity.TemplateParts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTemplatePostInput {
    private String title;
    private String description;
    private LocalDate createdDate;
    private List<String> categories;
}
