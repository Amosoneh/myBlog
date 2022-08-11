package africa.semicolon.myBlog.services;

import africa.semicolon.myBlog.data.models.Article;
import africa.semicolon.myBlog.data.models.Comment;
import africa.semicolon.myBlog.data.models.User;
import africa.semicolon.myBlog.dtos.requests.ArticleRequest;
import africa.semicolon.myBlog.dtos.requests.CommentRequest;
import africa.semicolon.myBlog.dtos.response.ArticleResponse;
import africa.semicolon.myBlog.utils.ArticleAndResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArticleService {
    ArticleAndResponse createArticle(ArticleRequest request);
    Article getArticle(ArticleRequest request);
    ArticleResponse deleteArticle(ArticleRequest request);


    void saveAllArticle(List<Article> articles);
//    void addComment(Comment comment);
    void saveArticle(Article articles);

}
