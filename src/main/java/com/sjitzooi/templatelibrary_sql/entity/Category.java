package com.sjitzooi.templatelibrary_sql.entity;

import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name= "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "name must not be blank or null")
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "categories")
    private List<TemplatePost> posts;
}
