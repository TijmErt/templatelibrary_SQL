package com.sjitzooi.templatelibrary_sql.entity.TemplateParts;

import com.sjitzooi.templatelibrary_sql.entity.Category;
import com.sjitzooi.templatelibrary_sql.entity.Review;
import com.sjitzooi.templatelibrary_sql.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotEmpty
    private String title;

    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String description;

    @PastOrPresent
    private LocalDate createdDate;

    @NotEmpty
    private String fileKey;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User author;

    @OneToMany(mappedBy = "reviewedPost")
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(name = "template_post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id") )
    private List<Category> categories;

    @Transient
    private double avgRating;

    public double getAvgRating(){
        if(avgRating != 0.0){
            return avgRating;
        }
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;  // Return 0 if there are no reviews
        }
        // Calculate the average rating
        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
}
