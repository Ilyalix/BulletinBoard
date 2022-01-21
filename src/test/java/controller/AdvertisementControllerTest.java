package controller;

import com.controller.AdvertisementController;
import com.domain.Advertisement;
import com.exception_handler.HandlerExceptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.AdvertisementService;
import com.util.AdvertisementUtil;
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
public class AdvertisementControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Mock
    AdvertisementService advertisementService;

    @InjectMocks
    AdvertisementController advertisementController;

    MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(advertisementController)
                .setControllerAdvice(HandlerExceptions.class)
                .build();
    }

    @Test
    public void shouldSaveValidAdvertisement() throws Exception {
        Mockito.doNothing().when(advertisementService).save(ArgumentMatchers.any(Advertisement.class));

        Advertisement advertisement = AdvertisementUtil.createAdvertisement();

        String json = OBJECT_MAPPER.writeValueAsString(advertisement);

        mockMvc.perform(post("/advertisement/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void shouldSaveNotValidAdvertisement() throws Exception {
        Mockito.doNothing().when(advertisementService).save(ArgumentMatchers.any(Advertisement.class));

        Advertisement advertisement = AdvertisementUtil.createAdvertisement();
        advertisement.setName("advertisementadvertisement");

        String json = OBJECT_MAPPER.writeValueAsString(advertisement);

        mockMvc.perform(post("/advertisement/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldUpdateAdvertisement() throws Exception {
        Mockito.doNothing().when(advertisementService).save(ArgumentMatchers.any(Advertisement.class));

        Advertisement advertisement = AdvertisementUtil.createAdvertisement();

        String json = OBJECT_MAPPER.writeValueAsString(advertisement);

        mockMvc.perform(put("/advertisement/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAdvertisement() throws Exception {
        Advertisement advertisement = AdvertisementUtil.createAdvertisement();

        advertisement.setId(5);

        Mockito.when(advertisementService.findById(ArgumentMatchers.anyInt())).thenReturn(advertisement);

        mockMvc.perform(get("/advertisement/{id}", 2))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("id").value(5))
                .andExpect(jsonPath("name").value("House"))
                .andExpect(jsonPath("author.phones[0].phone").value("088"))
                .andExpect(jsonPath("category.name").value("House"));
    }

    @Test
    public void shouldDeleteAdvertisement() throws Exception {

        Mockito.doNothing().when(advertisementService).deleteById(ArgumentMatchers.anyInt());

        mockMvc.perform(delete("/advertisement/{id}", 2))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldGetAdvertisementByCategory() throws Exception {
        Advertisement advertisement = AdvertisementUtil.createAdvertisement();

        advertisement.getCategory().setId(5);

        List<Advertisement> list = Arrays.asList(advertisement);

        Mockito.when(advertisementService.findAdvertisementByCategory(ArgumentMatchers.anyInt())).thenReturn(list);

        mockMvc.perform(get("/advertisement/category/{id}", 2))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].category.id").value(5))
                .andExpect(jsonPath("$[0].name").value("House"))
                .andExpect(jsonPath("$[0].author.phones[0].phone").value("088"))
                .andExpect(jsonPath("$[0].category.name").value("House"));
    }

    @Test
    public void shouldGetAdvertisementByCategories() throws Exception {
        Advertisement advertisement1 = AdvertisementUtil.createAdvertisement();
        Advertisement advertisement2 = AdvertisementUtil.createAdvertisement();

        advertisement1.getCategory().setId(4);
        advertisement1.getCategory().setName("Computer");

        advertisement2.getCategory().setId(5);
        advertisement2.getCategory().setName("Moto");
        List<Advertisement> list = Arrays.asList(advertisement1, advertisement2);

        Mockito.when(advertisementService.findAdvertisementByCategories(ArgumentMatchers.anyList())).thenReturn(list);

        mockMvc.perform(get("/advertisement/categories/{ids}", 2, 3))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].category.id").value(4))
                .andExpect(jsonPath("$[0].category.name").value("Computer"))
                .andExpect(jsonPath("$[1].category.id").value(5))
                .andExpect(jsonPath("$[1].category.name").value("Moto"));
    }

    @Test
    public void shouldSearchByWord() throws Exception {
        Advertisement advertisement = AdvertisementUtil.createAdvertisement();

        advertisement.setText("description");

        List<Advertisement> list = Arrays.asList(advertisement);

        Mockito.when(advertisementService.searchByWord(ArgumentMatchers.anyString())).thenReturn(list);

        mockMvc.perform(get("/advertisement/word/{word}", "word"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].text").value("description"))
                .andExpect(jsonPath("$[0].name").value("House"))
                .andExpect(jsonPath("$[0].author.phones[0].phone").value("088"))
                .andExpect(jsonPath("$[0].category.name").value("House"));
    }

    @Test
    public void shouldSearchByDate() throws Exception {
        Advertisement advertisement = AdvertisementUtil.createAdvertisement();

        List<Advertisement> list = Arrays.asList(advertisement);

        Mockito.when(advertisementService.searchByDate(ArgumentMatchers.any())).thenReturn(list);

        mockMvc.perform(get("/advertisement/date/{date}", "2018-08-08"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].dateOfPublic[0]").value("2022"))
                .andExpect(jsonPath("$[0].name").value("House"));
    }
}
