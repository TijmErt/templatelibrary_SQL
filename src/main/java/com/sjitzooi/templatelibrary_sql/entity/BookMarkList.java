package com.sjitzooi.templatelibrary_sql.entity;


import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "bookmark_list")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookMarkList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User listOwner;

    @ManyToMany
    @JoinTable(
            name = "bookmark_list_template_post",
            joinColumns = @JoinColumn(name = "bookmark_list_id"),
            inverseJoinColumns = @JoinColumn(name = "template_post_id") )
    private List<TemplatePost> bookMarkedPosts;
}
