package com.sjitzooi.templatelibrary_sql.entity.TemplateParts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplatePostConnection {
    private List<TemplatePost> posts;
    private PageInfo pageInfo;
}
