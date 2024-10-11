package com.sjitzooi.templatelibrary_sql.repository;

import com.sjitzooi.templatelibrary_sql.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    public List<Review> findByAuthor_Id(String author);
}
