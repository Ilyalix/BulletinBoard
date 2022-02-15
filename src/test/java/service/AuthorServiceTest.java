package service;

import com.domain.Author;
import com.domain.Role;
import com.repository.AuthorRepository;
import com.repository.RoleRepository;
import com.service.AuthorService;
import com.util.AuthorUtil;
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

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigAppTest.class)
@WebAppConfiguration
@Sql(scripts = {
        "classpath:sql_scripts/truncate_email_table.sql",
        "classpath:sql_scripts/truncate_phone_table.sql",
        "classpath:sql_scripts/truncate_address_table.sql",
        "classpath:sql_scripts/truncate_author_table.sql",
        "classpath:sql_scripts/truncate_advertisement_table.sql",
        "classpath:sql_scripts/truncate_role_table.sql",
        "classpath:sql_scripts/truncate_author_role_table.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AuthorServiceTest {

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorRepository repository;

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
        repository.save(author);
    }

    @Before
    public void saveBeforeAuthor() {
        saveRole();
        saveAuthor();
    }

    @Test
    public void shouldSaveAuthor() {
        Author author = repository.findById(1).get();
        String phone = author.getPhones().get(0).getPhone();
        String email = author.getEmail().getEmail();
        String city = author.getAddress().getCity();

        Assert.assertEquals("Masha", author.getName());
        Assert.assertEquals("088", phone);
        Assert.assertEquals("gmail.com", email);
        Assert.assertEquals("orsk", city);
    }

    @Test
    public void shouldUpdateAuthor() {
        Author author = repository.findById(1).get();
        author.setName("Pasha");

        authorService.update(author);

        Assert.assertEquals("Pasha", author.getName());
    }

    @Test
    public void shouldGetAuthor() {
        Author author = authorService.findById(1);
        Assert.assertEquals("Masha", author.getName());
    }

    @Test
    public void shouldDeleteAuthor() {
        authorService.deleteById(1);

        boolean exists = repository.existsById(1);

        Assert.assertFalse(exists);
    }


}
