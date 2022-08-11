package africa.semicolon.myBlog.utils;

import africa.semicolon.myBlog.data.models.Article;
import africa.semicolon.myBlog.dtos.response.ArticleResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class ArticleAndResponse {
    private Article article;
    private ArticleResponse articleResponse;
    public ArticleAndResponse(Article article, ArticleResponse response) {
        this.article = article;
        this.articleResponse = response;
    }
}
