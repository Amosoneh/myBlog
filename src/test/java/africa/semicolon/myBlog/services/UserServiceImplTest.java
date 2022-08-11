package africa.semicolon.myBlog.services;

import africa.semicolon.myBlog.data.repositories.ArticleRepository;
import africa.semicolon.myBlog.data.repositories.BlogRepository;
import africa.semicolon.myBlog.data.repositories.UserRepository;
import africa.semicolon.myBlog.dtos.requests.ArticleRequest;
import africa.semicolon.myBlog.dtos.requests.BlogRequest;
import africa.semicolon.myBlog.dtos.requests.LogInRequest;
import africa.semicolon.myBlog.dtos.requests.RegisterUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    private BlogRepository blogRepository;
    @BeforeEach
    void setUp(){
        userRepository.deleteAll();
    }
    @Test
    public void registerUser_repositorySizeIsOneTest(){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUserName("amos");
        request.setPassword("12345");
        userService.registerUser(request);
        assertEquals(1L, userRepository.count());
        assertThat(userRepository.count(), is(1L));
    }

    @Test
    public void logInUserTest(){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUserName("amos");
        request.setPassword("12345");
        userService.registerUser(request);
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUserName(request.getUserName());
        logInRequest.setPassword(request.getPassword());
        userService.logInUser(logInRequest);
        assertNotNull(userService);
        assertEquals("amos", userService.getUserName(logInRequest.getUserName()));
    }
    @Test
    public void userCanCreateBlogTest(){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUserName("amos");
        request.setPassword("12345");
        userService.registerUser(request);
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUserName(request.getUserName());
        logInRequest.setPassword(request.getPassword());
        var savedUser = userService.logInUser(logInRequest);
        BlogRequest blogRequest = new BlogRequest();
        blogRequest.setUserId(savedUser.getId());
        blogRequest.setName("Semicolon blog");
        userService.createBlog(blogRequest);
        savedUser = userService.findUserById(savedUser.getId());
        assertNotNull(savedUser);
        assertEquals("Semicolon blog", savedUser.getBlog().getName());
    }

    @Test
    public void userCreateArticleTest(){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUserName("amos");
        request.setPassword("12345");
        userService.registerUser(request);
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUserName(request.getUserName());
        logInRequest.setPassword(request.getPassword());
        var savedUser = userService.logInUser(logInRequest);
        BlogRequest blogRequest = new BlogRequest();
        blogRequest.setUserId(savedUser.getId());
        blogRequest.setName("Semicolon blog");
        userService.createBlog(blogRequest);
        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setTitle("Programming");
        articleRequest.setBody("I love programming");
        articleRequest.setUserId(savedUser.getId());
        userService.createArticle(articleRequest);
        savedUser = userService.findUserById(savedUser.getId());
        assertEquals(1L, articleRepository.count());
    }



}