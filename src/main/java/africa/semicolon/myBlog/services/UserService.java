package africa.semicolon.myBlog.services;

import africa.semicolon.myBlog.data.models.Article;
import africa.semicolon.myBlog.data.models.User;
import africa.semicolon.myBlog.dtos.requests.*;
import africa.semicolon.myBlog.dtos.response.*;
import africa.semicolon.myBlog.utils.ArticleAndResponse;

import java.util.List;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);
    User logInUser(LogInRequest request);
    String getUserName(String userName);
    List<Article> viewUserArticle(ArticleRequest request);
    ArticleResponse deleteArticle(ArticleRequest request);

    User findUserById(String id);

    void save(User user);

    Article getArticle(ArticleRequest request);

    BlogResponse createBlog(BlogRequest request);

    ArticleAndResponse createArticle(ArticleRequest request);

    CommentResponse addComment(CommentRequest request);
}
