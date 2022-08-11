package africa.semicolon.myBlog.data.repositories;

import africa.semicolon.myBlog.data.models.Article;
import africa.semicolon.myBlog.dtos.requests.ArticleRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {
    Article findArticleById(String id);
}
