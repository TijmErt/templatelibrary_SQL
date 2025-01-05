package com.sjitzooi.templatelibrary_sql.entity;


import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Size(min = 0, max = 300 ,message = "content must be between 0 and 300 characters")
    private String content;

    @Min(value = 0, message = "rating must not be lower than 0")
    @Max(value = 5, message = "rating must not be higher than 5")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "template_post_id")
    private TemplatePost reviewedPost;
}
