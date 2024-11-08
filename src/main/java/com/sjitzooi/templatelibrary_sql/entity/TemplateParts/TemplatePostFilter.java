package com.sjitzooi.templatelibrary_sql.entity.TemplateParts;

import com.sjitzooi.templatelibrary_sql.entity.Category;
import lombok.Data;

import java.util.List;

@Data
public class TemplatePostFilter {
    private String name;
    private List<Category> categories;// for name search
    private String sortBy; // e.g., "name", "createdDate"
    private Boolean ascending; //
}
