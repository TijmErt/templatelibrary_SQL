package com.sjitzooi.templatelibrary_sql.integrationtest;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.test.EnableDgsTest;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;

import com.sjitzooi.templatelibrary_sql.entity.User;
import com.sjitzooi.templatelibrary_sql.service.NoSQLCallerService;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@EnableDgsTest
class TemplatePostControllerIntegrationTest {

    @MockBean
    private NoSQLCallerService noSQLCallerService;


    @Autowired
    private DgsQueryExecutor dgsQueryExecutor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreateTemplatePost() {
        //Arrange
        //Mocks Security Context Users (allows roles to function)
        SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext());
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "password",
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Act
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.pdf",
                "application/pdf",
                new byte[]{0x25, 0x50, 0x44, 0x46, 0x2D} /* Simplified PDF header bytes*/);

        String mutation = """
        mutation createTemplatePost($file: Upload!, $input: TemplatePostInput!) {
            createTemplatePost(file: $file, input: $input)
        }
    """;


        // Prepare the variables
        Map<String, Object> variables = new HashMap<>();

        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("title", "New Post");
        inputMap.put("description", "New post description");
        inputMap.put("createdDate", "2024-11-05");
        inputMap.put("authorId", "user-id-1");
        variables.put("file", file); // Attach the file
        variables.put("input", inputMap); // Attach input object

        when(noSQLCallerService.uploadFile(any())).thenReturn("documentKey");


        HttpHeaders headers = new HttpHeaders();
        String userCredentials = Base64.getEncoder().encodeToString("user:password".getBytes());
        headers.set(HttpHeaders.AUTHORIZATION, "Basic " + userCredentials);
        // Assert
        String result = dgsQueryExecutor.executeAndExtractJsonPathAsObject(mutation, "data.createTemplatePost", variables, String.class, headers);
        assertNotNull(result, "createTemplatePost should return an ID");
        assertFalse(result.isEmpty(), "Returned ID should not be empty");

        verify(noSQLCallerService).uploadFile(any());
    }

    @Test
    void testGetTemplatePost() {

        //Arrange
        User expectedAuthor = new User();
        expectedAuthor.setId("user-id-1");
        expectedAuthor.setEmail("alice@test.com");
        expectedAuthor.setUserName("Alice");
        TemplatePost expected = new TemplatePost(
                "tempPost-id-1",
                "Template Post Title 1",
                "Description for template post 1",
                LocalDate.parse("2024-10-29"),
                "Key1",
                expectedAuthor,
                new ArrayList<>(),
                new ArrayList<>()
        );
        // Act
        String query = """
            query {
                getTemplatePost(id: "tempPost-id-1") {
                    id
                    title
                    description
                    createdDate
                    author{
                        id
                        email
                        userName
                    }
                    categories{
                        id
                        name
                    }
                }
            }
        """;

        // Execute the query and assert the response
        TemplatePost result = dgsQueryExecutor.executeAndExtractJsonPathAsObject(query, "data.getTemplatePost", TemplatePost.class);

        assertNotNull(result, "Result should not be null");
        assertEquals(expected.getTitle(), result.getTitle(), "Title should match");
        assertEquals(expected.getDescription(), result.getDescription(), "description should match");
        assertEquals(expected.getCreatedDate(), result.getCreatedDate(), "createdDate should match");

        assertEquals(expected.getAuthor().getId(), result.getAuthor().getId(), "Author id should match");
        assertEquals(expected.getAuthor().getEmail(), result.getAuthor().getEmail(), "Author email should match");
        assertEquals(expected.getAuthor().getUserName(), result.getAuthor().getUserName(), "Author userName should match");

    }
}
