package com.sjitzooi.templatelibrary_sql.entity;


import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "review")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String content;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "template_post_id")
    private TemplatePost reviewedPost;
}
