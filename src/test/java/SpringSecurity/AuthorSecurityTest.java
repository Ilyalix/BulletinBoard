package SpringSecurity;

import com.controller.AuthorController;
import config.ConfigAppTest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigAppTest.class)
@WebAppConfiguration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorSecurityTest {

    @Autowired
    AuthorController authorController;

    @Autowired
    UserDetailsService service;


    @Test
    public void shouldGetAuthorOnSecured() throws Exception {
        UserDetails userDetails = service.loadUserByUsername("Pasha");
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        authorController.findById(1);
    }

    @Test
    public void shouldGetAuthorWithInvalidRoleOnSecured() throws Exception {
        UserDetails userDetails = service.loadUserByUsername("Pasha");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_INVALID"));
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authToken);
        authorController.findById(1);
    }
}

