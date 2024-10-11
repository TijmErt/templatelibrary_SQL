package com.sjitzooi.templatelibrary_sql.entity.TemplateParts;

import com.sjitzooi.templatelibrary_sql.entity.Review;
import com.sjitzooi.templatelibrary_sql.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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

    private Date createddate;

    private String documentKey;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(mappedBy = "reviewedPost")
    private List<Review> reviews;
}
