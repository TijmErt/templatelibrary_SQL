package com.sjitzooi.templatelibrary_sql.entity.TemplateParts;

import com.sjitzooi.templatelibrary_sql.entity.Category;
import com.sjitzooi.templatelibrary_sql.entity.Review;
import com.sjitzooi.templatelibrary_sql.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "template_post" )
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TemplatePost {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private String description;

    private LocalDate createdDate;

    private String documentKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User author;

    @OneToMany(mappedBy = "reviewedPost")
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(name = "template_post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id") )
    private List<Category> categories;
}
