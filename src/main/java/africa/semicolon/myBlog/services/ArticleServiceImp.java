package africa.semicolon.myBlog.services;

import africa.semicolon.myBlog.data.models.Article;
import africa.semicolon.myBlog.data.models.Comment;
import africa.semicolon.myBlog.data.repositories.ArticleRepository;
import africa.semicolon.myBlog.dtos.requests.ArticleRequest;
import africa.semicolon.myBlog.dtos.requests.CommentRequest;
import africa.semicolon.myBlog.dtos.response.ArticleResponse;
import africa.semicolon.myBlog.exceptions.ArticleNotFoundException;
import africa.semicolon.myBlog.utils.ArticleAndResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService{
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public ArticleAndResponse createArticle(ArticleRequest request) {
        Article article = new Article();
        article.setBody(request.getBody());
        article.setTitle(request.getTitle());
        var savedArt = articleRepository.save(article);
        ArticleResponse response = new ArticleResponse();
        response.setMessage("Article created successfully");
        return new ArticleAndResponse(savedArt, response);

    }

    @Override
    public Article getArticle(ArticleRequest request) {
        var article = articleRepository.findArticleById(request.getId());
        if (article==null) {
            throw new ArticleNotFoundException("Article not found or have been removed");
        }
        return article;
    }



    @Override
    public ArticleResponse deleteArticle(ArticleRequest request) {
        Article article = articleRepository.findArticleById(request.getId());
        if (article == null) throw new ArticleNotFoundException("Article with id "+request.getId()+ " not found");
        articleRepository.delete(article);
        ArticleResponse response = new ArticleResponse();
        response.setMessage("Article successfully deleted");
        return response;
    }

    @Override
    public void saveAllArticle(List<Article> articles) {
        articleRepository.saveAll(articles);
    }

//    @Override
//    public void addComment(Comment comment) {
//        ArticleRequest articleRequest = new ArticleRequest();
//        CommentRequest commentRequest = new CommentRequest();
//        commentRequest.setComment(comment.getComment());
//        var article = articleRepository.findArticleById(articleRequest.getId());
//        var savedComment = commentService.createComment(commentRequest);
//        article.getComments().add(savedComment);
//        ArticleService articleService = new ArticleServiceImp();
//        articleService.saveArticle(article);
//    }

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(article);
    }


}
