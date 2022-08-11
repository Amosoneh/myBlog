package africa.semicolon.myBlog.data.repositories;

import africa.semicolon.myBlog.data.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTest(){
        User user = new User();
        var savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
        assertThat(savedUser.getId(), is(notNullValue()));
    }

}