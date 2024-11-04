package com.sjitzooi.templatelibrary_sql.entity;

import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import jakarta.persistence.*;

import java.util.List;

@Entity(name= "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "categories")
    private List<TemplatePost> posts;
}
