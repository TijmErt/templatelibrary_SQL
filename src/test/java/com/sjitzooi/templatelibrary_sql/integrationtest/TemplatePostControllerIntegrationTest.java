package com.sjitzooi.templatelibrary_sql.integrationtest;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.test.EnableDgsTest;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePostInput;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePostModel;
import com.sjitzooi.templatelibrary_sql.entity.User;
import com.sjitzooi.templatelibrary_sql.service.TemplatePostService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@EnableDgsTest
public class TemplatePostControllerIntegrationTest {

    @Autowired
    private DgsQueryExecutor dgsQueryExecutor;

//    @Test
//    public void testCreateTemplatePost() {
//        // Act
//        String mutation = """
//            mutation {
//                createTemplatePost(input: {
//                    title: "New Post",
//                    description: "New post description",
//                    createdDate: "2024-11-05",
//                    authorId: "user-id-1",
//                    documentName: "New Document",
//                    documentType: "application/pdf"
//                }) {
//                    templatePost {
//                        id
//                        title
//                        description
//                        createdDate
//                    }
//                    documentModel {
//                        documentName
//                        documentType
//                    }
//                }
//            }
//        """;
//        //Assert
//        TemplatePostModel result = dgsQueryExecutor.executeAndExtractJsonPathAsObject(mutation, "data.createTemplatePost", TemplatePostModel.class);
//        assertNotNull(result, "createTemplatePost should not be null");
//
//        assertNotNull(result.getTemplatePost(), "TemplatePost should not be null");
//        assertEquals("New Post", result.getTemplatePost().getTitle(), "Title should match");
//        assertEquals("New post description", result.getTemplatePost().getDescription(), "description should match");
//        assertEquals(LocalDate.parse("2024-11-05"), result.getTemplatePost().getCreatedDate(), "description should match");
//
//        assertNotNull(result.getDocumentModel(), "DocumentModel should not be null");
//        assertEquals("New Document", result.getDocumentModel().getDocumentName(), "DocumentName should match");
//        assertEquals("application/pdf", result.getDocumentModel().getDocumentType(), "DocumentName should match");
//    }

//    @Test
//    public void testGetTemplatePost() {
//
//        //Arrange
//        User expectedAuthor = new User();
//        expectedAuthor.setId("user-id-1");
//        expectedAuthor.setEmail("alice@test.com");
//        expectedAuthor.setUserName("Alice");
//        TemplatePost expected = new TemplatePost(
//                "tempPost-id-1",
//                "Template Post Title 1",
//                "Description for template post 1",
//                LocalDate.parse("2024-10-29"),
//                "Key1",
//                expectedAuthor,
//                new ArrayList<>(),
//                new ArrayList<>()
//        );
//        // Act
//        String query = """
//            query {
//                getTemplatePost(id: "tempPost-id-1") {
//                    templatePost {
//                        id
//                        title
//                        description
//                        createdDate
//                        author{
//                            id
//                            email
//                            userName
//                        }
//                    }
//                    documentModel {
//                        documentName
//                        documentType
//                    }
//                }
//            }
//        """;
//
//        // Execute the query and assert the response
//        TemplatePostModel result = dgsQueryExecutor.executeAndExtractJsonPathAsObject(query, "data.getTemplatePost", TemplatePostModel.class);
//
//        assertNotNull(result, "Result should not be null");
//        assertNotNull(result.getTemplatePost(), "TemplatePost should not be null");
//        assertEquals(expected.getTitle(), result.getTemplatePost().getTitle(), "Title should match");
//        assertEquals(expected.getDescription(), result.getTemplatePost().getDescription(), "description should match");
//        assertEquals(expected.getCreatedDate(), result.getTemplatePost().getCreatedDate(), "createdDate should match");
//
//        assertEquals(expected.getAuthor().getId(), result.getTemplatePost().getAuthor().getId(), "Author id should match");
//        assertEquals(expected.getAuthor().getEmail(), result.getTemplatePost().getAuthor().getEmail(), "Author email should match");
//        assertEquals(expected.getAuthor().getUserName(), result.getTemplatePost().getAuthor().getUserName(), "Author userName should match");
//
//        assertNotNull(result.getDocumentModel(), "DocumentModel should not be null");
//
//        /* Temporary should be changed when related service class gets mocked in the future */
//        assertEquals(".tempType", result.getDocumentModel().getDocumentType(), "DocumentType should match");
//        assertEquals("tempDocName", result.getDocumentModel().getDocumentName(), "DocumentName should match");
//    }
}
