package africa.semicolon.myBlog.services;


import africa.semicolon.myBlog.data.repositories.ArticleRepository;
import africa.semicolon.myBlog.dtos.requests.ArticleRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleServiceImpTest {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;
    private ArticleRequest request;
    private ArticleRequest request1;
    @BeforeEach
    void setUp(){
        request = new ArticleRequest();
        request1 = new ArticleRequest();
        request.setTitle("Beautiful Me");
        request.setBody("I love my self");
        articleRepository.deleteAll();

    }
    @Test
    public void createArticleTest(){
        articleService.createArticle(request);
        assertNotNull(articleService);
        assertEquals(1L, articleRepository.count());
    }

    @Test
    public void getArticleTest(){
        var savedArticle = articleService.createArticle(request);
        request1.setTitle(savedArticle.getArticle().getTitle());
        request1.setBody(savedArticle.getArticle().getBody());
        request1.setId(savedArticle.getArticle().getId());
        var returnedArticle = articleService.getArticle(request1);
        assertEquals("Beautiful Me", articleRepository.findById(returnedArticle.getId()).get().getTitle());
    }

    @Test
    public void deleteArticleTest(){
        var savedArticle = articleService.createArticle(request);
        assertNotNull(articleService);
        request1.setTitle(savedArticle.getArticle().getTitle());
        request1.setId(savedArticle.getArticle().getId());
        request1.setBody(savedArticle.getArticle().getBody());
        articleService.deleteArticle(request1);
        assertEquals(0, articleRepository.count());
    }


}