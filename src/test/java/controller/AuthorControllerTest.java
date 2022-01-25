package controller;

import com.controller.AuthorController;
import com.domain.Advertisement;
import com.domain.Author;
import com.exception_handler.HandlerExceptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.AuthorService;
import com.util.AdvertisementUtil;
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

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigAppTest.class)
@WebAppConfiguration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorControllerTest {
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
    public void shouldSaveValidAuthor() throws Exception {
        Mockito.doNothing().when(authorService).save(ArgumentMatchers.any(Author.class));

        Author author = AuthorUtil.createAuthor();

        String json = OBJECT_MAPPER.writeValueAsString(author);

        mockMvc.perform(post("/author/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSaveNotValidAuthor() throws Exception {
        Mockito.doNothing().when(authorService).save(ArgumentMatchers.any(Author.class));

        Author author = AuthorUtil.createAuthor();
        author.setName("authorauthor");

        String json = OBJECT_MAPPER.writeValueAsString(author);

        mockMvc.perform(post("/author/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldUpdateAuthor() throws Exception {
        Mockito.doNothing().when(authorService).save(ArgumentMatchers.any(Author.class));

        Author author = AuthorUtil.createAuthor();

        String json = OBJECT_MAPPER.writeValueAsString(author);

        mockMvc.perform(put("/author/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAuthor() throws Exception {
        Author author = AuthorUtil.createAuthor();
        author.setId(5);

        Mockito.when(authorService.findById(ArgumentMatchers.anyInt())).thenReturn(author);

        mockMvc.perform(get("/author/{id}", 2))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("id").value(5))
                .andExpect(jsonPath("name").value("Masha"))
                .andExpect(jsonPath("address.city").value("orsk"))
                .andExpect(jsonPath("phones[0].phone").value("088"));
    }

    @Test
    public void shouldDeleteAuthor() throws Exception {

        Mockito.doNothing().when(authorService).deleteById(ArgumentMatchers.anyInt());

        mockMvc.perform(delete("/author/{id}", 2))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldFindAdvertisementByIdAuthor() throws Exception {
        Advertisement advertisement1 = AdvertisementUtil.createAdvertisement();
        Advertisement advertisement2 = AdvertisementUtil.createAdvertisement();

        advertisement1.getAuthor().setId(3);
        advertisement2.getAuthor().setId(4);
        List<Advertisement> list = Arrays.asList(advertisement1, advertisement2);


        Mockito.when(authorService.findAdvertisementByIdAuthor(ArgumentMatchers.anyList())).thenReturn(list);

        mockMvc.perform(get("/author/get-ad-author/{ids}", 2, 3))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].author.id").value(3))
                .andExpect(jsonPath("$[1].author.id").value(4));
    }
}
