package com.sjitzooi.templatelibrary_sql.repository;

import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TemplatePostRepository extends JpaRepository<TemplatePost, String> {
    @Query(value =
                    "SELECT tp, ROUND(COALESCE(AVG(r.rating), 1), 1) AS avgRating FROM template_post tp" +
                            "                     LEFT JOIN review r ON tp.id = r.reviewedPost.id " +
                            "WHERE (:searchTerm IS NULL OR tp.title ILIKE '%' || :searchTerm || '%')" +
                            "group by tp.id, tp.createdDate, tp.description, tp.fileKey, tp.title, tp.author.id")
    Page<Object[]> getFilteredTemplatePosts(@Param("searchTerm") String searchTerm,
                                                 Pageable pageable);
}
