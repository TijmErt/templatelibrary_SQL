package com.sjitzooi.templatelibrary_sql;

import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePostInput;
import com.sjitzooi.templatelibrary_sql.entity.User;
import com.sjitzooi.templatelibrary_sql.repository.TemplatePostRepository;
import com.sjitzooi.templatelibrary_sql.service.TemplatePostService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sjitzooi.templatelibrary_sql.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

public class TemplatePostServiceTest {
    @Mock
    private TemplatePostRepository templatePostRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TemplatePostService templatePostService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testGetAll() {
//
//        TemplatePost post1 = new TemplatePost("1", "Template 1","Description",LocalDate.now(),"",new User(),new ArrayList<>(),new ArrayList<>());
//        TemplatePost post2 = new TemplatePost("2", "Template 2","Description",LocalDate.now(),"",new User(),new ArrayList<>(),new ArrayList<>());
//        List<TemplatePost> mockPosts = Arrays.asList(post1, post2);
//
//        when(templatePostRepository.findAll()).thenReturn(mockPosts);
//
//        List<TemplatePost> result = templatePostService.getAll();
//        assertEquals(2, result.size());
//        assertEquals("Template 1", result.get(0).getTitle());
//        assertEquals("Template 2", result.get(1).getTitle());
//    }

    @Test
    void testGetById_Found() {
        //Arrange
        TemplatePost post = new TemplatePost("1", "Template 1","Description", LocalDate.now(),"",new User(),new ArrayList<>(),new ArrayList<>());
        when(templatePostRepository.findById("1")).thenReturn(Optional.of(post));

        //Act
        TemplatePost result = templatePostService.getById("1");
        //Assert
        assertEquals("Template 1", result.getTitle());
    }

    @Test
    void testGetById_NotFound() {
        //Arrange
        when(templatePostRepository.findById("2")).thenReturn(Optional.empty());

        //Act
        TemplatePost result = templatePostService.getById("2");

        //Assert
        assertNull(result);
    }

    @Test
    void testCreateTemplatePost() {
        //Arrange
        TemplatePostInput input = new TemplatePostInput();
        input.setTitle("Sample Title");
        input.setDescription("Sample Description");
        input.setCreatedDate(LocalDate.now());
        input.setAuthorId("authorId");
        input.setDocumentName("document.pdf");
        input.setDocumentType("application/pdf");

        User mockUser = new User();
        mockUser.setId("authorId");
        when(userService.getById("authorId")).thenReturn(mockUser);

        TemplatePost expectedTemplatePost = new TemplatePost();
        expectedTemplatePost.setTitle(input.getTitle());
        expectedTemplatePost.setDescription(input.getDescription());
        expectedTemplatePost.setCreatedDate(input.getCreatedDate());
        expectedTemplatePost.setDocumentKey("documentKey");
        expectedTemplatePost.setAuthor(mockUser);

        when(templatePostRepository.save(any(TemplatePost.class))).thenReturn(expectedTemplatePost);

        //Act
        TemplatePost actualTemplatePost = templatePostService.save(input);

        //Assert
        verify(userService).getById("authorId");
        verify(templatePostRepository).save(any(TemplatePost.class));
        assertEquals(expectedTemplatePost.getTitle(), actualTemplatePost.getTitle());
        assertEquals(expectedTemplatePost.getDescription(), actualTemplatePost.getDescription());
        assertEquals(expectedTemplatePost.getCreatedDate(), actualTemplatePost.getCreatedDate());
        assertEquals(expectedTemplatePost.getDocumentKey(), actualTemplatePost.getDocumentKey());
        assertEquals(expectedTemplatePost.getAuthor(), actualTemplatePost.getAuthor());
    }
}
