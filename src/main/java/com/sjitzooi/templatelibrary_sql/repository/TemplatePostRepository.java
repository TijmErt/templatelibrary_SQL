package com.sjitzooi.templatelibrary_sql.repository;

import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplatePostRepository extends JpaRepository<TemplatePost, String> {
}
