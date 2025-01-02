package com.sjitzooi.templatelibrary_sql.unittest;

import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePostInput;
import com.sjitzooi.templatelibrary_sql.entity.User;
import com.sjitzooi.templatelibrary_sql.repository.TemplatePostRepository;
import com.sjitzooi.templatelibrary_sql.service.NoSQLCallerService;
import com.sjitzooi.templatelibrary_sql.service.TemplatePostService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sjitzooi.templatelibrary_sql.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.util.*;

public class TemplatePostServiceTest {
    @Mock
    private TemplatePostRepository templatePostRepository;

    @Mock
    private UserService userService;

    @Mock
    private NoSQLCallerService noSQLCallerService;

    @InjectMocks
    private TemplatePostService templatePostService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById_Found() {
        //Arrange
        TemplatePost post = new TemplatePost("1", "Template 1","Description", LocalDate.now(),"",new User(),new ArrayList<>(),new ArrayList<>(),0.0);
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
        // Arrange
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "test.pdf",
                "application/pdf",
                new byte[]{0x25, 0x50, 0x44, 0x46, 0x2D});
        when(noSQLCallerService.uploadFile(mockMultipartFile)).thenReturn("documentKey");

        TemplatePostInput input = new TemplatePostInput();
        input.setTitle("Sample Title");
        input.setDescription("Sample Description");
        input.setCreatedDate(LocalDate.now());
        input.setAuthorId("authorId");

        User mockUser = new User();
        mockUser.setId("authorId");
        when(userService.getById("authorId")).thenReturn(mockUser);

        // Act
        TemplatePost actualTemplatePost = templatePostService.save(mockMultipartFile, input);

        // Assert
        verify(userService).getById("authorId");
        verify(noSQLCallerService).uploadFile(mockMultipartFile);

        ArgumentCaptor<TemplatePost> captor = ArgumentCaptor.forClass(TemplatePost.class);
        verify(templatePostRepository).save(captor.capture());

        TemplatePost capturedTemplatePost = captor.getValue();
        assertEquals(input.getTitle(), capturedTemplatePost.getTitle());
        assertEquals(input.getDescription(), capturedTemplatePost.getDescription());
        assertEquals(input.getCreatedDate(), capturedTemplatePost.getCreatedDate());
        assertEquals(mockUser, capturedTemplatePost.getAuthor());

        assertEquals(actualTemplatePost.getId(), capturedTemplatePost.getId());
        assertEquals(actualTemplatePost.getTitle(), capturedTemplatePost.getTitle());
        assertEquals(actualTemplatePost.getDescription(), capturedTemplatePost.getDescription());
        assertEquals(actualTemplatePost.getCreatedDate(), capturedTemplatePost.getCreatedDate());
        assertEquals(actualTemplatePost.getAuthor(), capturedTemplatePost.getAuthor());

        assertEquals("documentKey", capturedTemplatePost.getFileKey());
    }
}
