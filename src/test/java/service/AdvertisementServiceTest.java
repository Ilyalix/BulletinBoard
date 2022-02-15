package service;

import com.domain.Advertisement;
import com.domain.Author;
import com.domain.Category;
import com.domain.Role;
import com.repository.AdvertisementRepository;
import com.repository.AuthorRepository;
import com.repository.CategoryRepository;
import com.repository.RoleRepository;
import com.service.AdvertisementService;
import com.util.AdvertisementUtil;
import com.util.AuthorUtil;
import com.util.CategoryUtil;
import com.util.RoleUtil;
import config.ConfigAppTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigAppTest.class)
@WebAppConfiguration
@Sql(scripts = {
        "classpath:sql_scripts/truncate_advertisement_table.sql",
        "classpath:sql_scripts/truncate_author_table.sql",
        "classpath:sql_scripts/truncate_email_table.sql",
        "classpath:sql_scripts/truncate_phone_table.sql",
        "classpath:sql_scripts/truncate_address_table.sql",
        "classpath:sql_scripts/truncate_category_table.sql",
        "classpath:sql_scripts/truncate_role_table.sql",
        "classpath:sql_scripts/truncate_author_role_table.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AdvertisementServiceTest {

    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RoleRepository roleRepository;

    private void saveRole() {
        Role role = RoleUtil.createRole();
        roleRepository.save(role);
    }

    private void saveAuthor() {
        Author author = AuthorUtil.createAuthor();
        Role role = roleRepository.getById(1);
        author.setRoles(Set.of(role));
        authorRepository.save(author);
    }

    private void saveCategory() {
        Category category = CategoryUtil.createCategory();
        categoryRepository.save(category);
    }


    @Before
    public void saveAdvertisement() {
        saveRole();
        saveAuthor();
        saveCategory();

        Advertisement advertisement = AdvertisementUtil.createAdvertisement();

        Author authorDB = authorRepository.getById(1);
        Category categoryDB = categoryRepository.getById(1);

        advertisement.setAuthor(authorDB);
        advertisement.setCategory(categoryDB);

        advertisementService.save(advertisement);
    }


    @Test
    public void shouldSaveAdvertisement() {
        Advertisement advertisement = advertisementService.findById(1);
        Assert.assertEquals("sale", advertisement.getText());
    }

    @Test
    public void shouldUpdateAdvertisement() {
        Advertisement advertisement = advertisementRepository.findById(1).get();
        advertisement.setName("Comp");
        advertisementService.update(advertisement);
        Assert.assertEquals("Comp", advertisement.getName());
    }


    @Test
    public void shouldGetAdvertisement() {
        Advertisement advertisement = advertisementRepository.findById(1).get();
        Assert.assertEquals("House", advertisement.getName());
    }

    @Test
    public void shouldDeleteAdvertisement() {
        advertisementService.deleteById(1);
        boolean exists = advertisementRepository.existsById(1);
        Assert.assertFalse(exists);
    }

    @Test
    public void shouldGetPagination() {
        List<Advertisement> paging = advertisementService.paging(0, 1);
        Assert.assertEquals("House", paging.get(0).getName());
    }
}
