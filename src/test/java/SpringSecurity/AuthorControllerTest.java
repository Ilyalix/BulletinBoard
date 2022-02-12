package SpringSecurity;

import com.controller.AuthorController;
import com.domain.Author;
import com.domain.Role;
import com.exception_handler.HandlerExceptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.RoleRepository;
import com.service.AuthorService;
import com.util.AuthorUtil;
import config.ConfigAppTest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Autowired
    UserDetailsService service;

    @Autowired
    WebApplicationContext context;

    @Autowired
    RoleRepository roleRepository;

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

        UserDetails userDetails = service.loadUserByUsername ("John");
        Authentication authToken = new UsernamePasswordAuthenticationToken (userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        authorService.findById(20);


    }

    @Test
    @WithMockUser(roles={"ADMIN"})
    public void mytest1() throws Exception {
        Role role = roleRepository.findById(2).get();
        Author author = AuthorUtil.createAuthor();
        author.setId(5);
        author.setRoles(Set.of(role));

        Mockito.when(authorController.findById(ArgumentMatchers.anyInt())).thenReturn(author);

        UserDetails userDetails = service.loadUserByUsername ("John");

        mockMvc.perform(get("/author/{id}", 2).with(user(userDetails)))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("id").value(5));
    }
}

