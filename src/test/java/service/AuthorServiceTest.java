package service;

import com.domain.Author;
import com.repository.AuthorRepository;
import com.service.AuthorService;
import com.util.AuthorUtil;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigAppTest.class)
@WebAppConfiguration
@Sql(scripts = {
        "classpath:sql_scripts/truncate_email_table.sql",
        "classpath:sql_scripts/truncate_phone_table.sql",
        "classpath:sql_scripts/truncate_address_table.sql",
        "classpath:sql_scripts/truncate_author_table.sql",
        "classpath:sql_scripts/truncate_advertisement_table.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AuthorServiceTest {

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorRepository repository;


    @Before
    public void saveAuthor(){
        Author author = AuthorUtil.createAuthor();
        authorService.save(author);
    }

    @Test
    public void shouldSaveAuthor(){
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
    public void shouldUpdateAuthor(){
        Author author = repository.findById(1).get();
        author.setName("Pasha");

        authorService.update(author);

        Assert.assertEquals("Pasha", author.getName());
    }

    @Test
    public void shouldGetAuthor(){
        Author author = authorService.findById(1);
        Assert.assertEquals("Masha", author.getName());
    }

    @Test
    public void shouldDeleteAuthor(){
        authorService.deleteById(1);

        boolean exists = repository.existsById(1);

        Assert.assertFalse(exists);
    }



}
