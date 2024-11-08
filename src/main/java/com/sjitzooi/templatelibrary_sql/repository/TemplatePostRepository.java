package com.sjitzooi.templatelibrary_sql.repository;

import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplatePostRepository extends JpaRepository<TemplatePost, String> {

//    @Query("SELECT tp FROM TemplatePost tp JOIN tp.categories t WHERE (:name IS NULL OR tp.name LIKE %:name%) " +
//            "AND (:tags IS NULL OR t.id IN :categories)")
//    public Page<TemplatePost> findFilteredTemplatePosts(@Param("name") String name,
//                                                 @Param("categories") List<String> categories,
//                                                 Pageable pageable);
}
