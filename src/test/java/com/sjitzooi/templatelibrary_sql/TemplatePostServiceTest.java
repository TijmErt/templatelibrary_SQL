package com.sjitzooi.templatelibrary_sql;

import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import com.sjitzooi.templatelibrary_sql.entity.User;
import com.sjitzooi.templatelibrary_sql.repository.TemplatePostRepository;
import com.sjitzooi.templatelibrary_sql.service.TemplatePostService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

public class TemplatePostServiceTest {
    @Mock
    private TemplatePostRepository templatePostRepository;

    @InjectMocks
    private TemplatePostService templatePostService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {

        TemplatePost post1 = new TemplatePost("1", "Template 1","Description",new Date(),"",new User(),new ArrayList<>());
        TemplatePost post2 = new TemplatePost("2", "Template 2","Description",new Date(),"",new User(),new ArrayList<>());
        List<TemplatePost> mockPosts = Arrays.asList(post1, post2);

        when(templatePostRepository.findAll()).thenReturn(mockPosts);

        List<TemplatePost> result = templatePostService.getAll();
        assertEquals(2, result.size());
        assertEquals("Template 1", result.get(0).getTitle());
        assertEquals("Template 2", result.get(1).getTitle());
    }

    @Test
    void testGetById_Found() {
        TemplatePost post = new TemplatePost("1", "Template 1","Description",new Date(),"",new User(),new ArrayList<>());
        when(templatePostRepository.findById("1")).thenReturn(Optional.of(post));

        TemplatePost result = templatePostService.getById("1");
        assertEquals("Template 1", result.getTitle());
    }

    @Test
    void testGetById_NotFound() {
        when(templatePostRepository.findById("2")).thenReturn(Optional.empty());

        TemplatePost result = templatePostService.getById("2");
        assertNull(result);
    }
}
