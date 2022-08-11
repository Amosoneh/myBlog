package africa.semicolon.myBlog.services;

import africa.semicolon.myBlog.data.repositories.CommentRepository;
import africa.semicolon.myBlog.dtos.requests.CommentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentServiceImpTest {
    @BeforeEach
    public void setUp(){
        commentRepository.deleteAll();
    }
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;

    @Test
    public void createCommentTest(){
        CommentRequest request = new CommentRequest();
        request.setComment("Yo are a bitch");
        commentService.createComment(request);
        assertNotNull(commentService);
        assertEquals(1L, commentRepository.count());
    }

    @Test
    public void deleteCommentTest(){
        CommentRequest request = new CommentRequest();
        request.setComment("Yo are a bitch");
        var comment = commentService.createComment(request);

        request.setComment("How are you doing");
        var comment2 = commentService.createComment(request);
        request.setId(comment2.getId());
        commentService.deleteComment(request);
        System.out.println(comment2);
        System.out.println(comment);
        assertEquals(1L, commentRepository.count());

    }

//    @Test
//    public void userArticleCanBeCommentedOnTest(){
//
//    }

}