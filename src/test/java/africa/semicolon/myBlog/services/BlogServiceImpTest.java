package africa.semicolon.myBlog.services;

import africa.semicolon.myBlog.data.repositories.BlogRepository;
import africa.semicolon.myBlog.dtos.requests.BlogRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogServiceImpTest {
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogRepository blogRepository;

    @Test
    public void createBlogTest() {
        BlogRequest request = new BlogRequest();
        request.setName("Semicolon Blog");
        blogService.createBlog(request);
        assertEquals(1L, blogRepository.count());
    }

}