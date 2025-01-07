package com.sjitzooi.templatelibrary_sql.entity;


import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.print.Book;
import java.util.List;

@Entity(name = "users" )
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_.-]*$", message = "User's userName contains invalid characters")
    private String userName;

    @NotBlank
    @Email(message = "Email should be valid")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author")
    private List<TemplatePost> myPosts;

    @OneToMany(mappedBy = "author")
    private List<Review> myReviews;

    @OneToMany(mappedBy = "listOwner")
    private List<BookMarkList> myBookMarkLists;

}
