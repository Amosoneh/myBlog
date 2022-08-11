package africa.semicolon.myBlog.controllers;

import africa.semicolon.myBlog.data.models.Article;
import africa.semicolon.myBlog.data.models.User;
import africa.semicolon.myBlog.dtos.requests.*;
import africa.semicolon.myBlog.dtos.response.ArticleResponse;
import africa.semicolon.myBlog.dtos.response.BlogResponse;
import africa.semicolon.myBlog.dtos.response.CommentResponse;
import africa.semicolon.myBlog.dtos.response.RegisterUserResponse;
import africa.semicolon.myBlog.exceptions.ArticleNotFoundException;
import africa.semicolon.myBlog.exceptions.CommentNotFoundException;
import africa.semicolon.myBlog.exceptions.UserExistException;
import africa.semicolon.myBlog.exceptions.UserNotFoundException;
import africa.semicolon.myBlog.services.ArticleService;
import africa.semicolon.myBlog.services.UserService;
import africa.semicolon.myBlog.utils.ArticleAndResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Service
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;


    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {
        try {
            RegisterUserResponse response = userService.registerUser(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (UserExistException error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> logInUser(@RequestBody LogInRequest request) {
        try {
            User user = userService.logInUser(request);
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } catch (UserNotFoundException error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/blog/articles")
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequest request) {
        try {
            ArticleAndResponse article = userService.createArticle(request);
            return new ResponseEntity<>(article.getArticleResponse(), HttpStatus.OK);
        } catch (ArticleNotFoundException error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/blog/article")
    public ResponseEntity<?> getArticle(@RequestBody ArticleRequest request) {
        try {
            Article article = userService.getArticle(request);
            return new ResponseEntity<>(article, HttpStatus.FOUND);
        } catch (ArticleNotFoundException error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/user/blog/articles")
    public ResponseEntity<?> deleteArticle(@RequestBody ArticleRequest request) {
        try {
            ArticleResponse response = userService.deleteArticle(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ArticleNotFoundException error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/user/blog/articles")
    public ResponseEntity<?> viewAllArticles(@RequestBody ArticleRequest request) {
        try {
            List<Article> articles = userService.viewUserArticle(request);
            return new ResponseEntity<>(articles, HttpStatus.OK);
        } catch (ArticleNotFoundException error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("user/blog")
    public ResponseEntity<?> createBlog(@RequestBody BlogRequest request) {
        try {
            BlogResponse response = userService.createBlog(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("user/blog/article")
    public ResponseEntity<?> addCommentToArticle(@RequestBody CommentRequest request){
        try {
            CommentResponse response = userService.addComment(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (ArticleNotFoundException error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
