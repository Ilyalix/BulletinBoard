package controller;

import SpringSecurity.WithMockUser;
import com.controller.AuthorController;
import com.domain.Author;
import com.exception_handler.HandlerExceptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.AuthorService;
import com.util.AuthorUtil;
import config.ConfigAppTest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigAppTest.class)
@WebAppConfiguration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorServiceTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Mock
    AuthorService authorService;

    @InjectMocks
    AuthorController authorController;

    MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(authorController)
                .setControllerAdvice(HandlerExceptions.class)
                .build();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldSaveAuthor() throws Exception {
        Mockito.doNothing().when(authorService).save(ArgumentMatchers.any(Author.class));

        Author author = AuthorUtil.createAuthor();

        String json = OBJECT_MAPPER.writeValueAsString(author);

        mockMvc.perform(post("/author/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
