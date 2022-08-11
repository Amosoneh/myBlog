package africa.semicolon.myBlog.data.repositories;

import africa.semicolon.myBlog.data.models.Blog;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogRepository extends MongoRepository<Blog, String> {
    Blog findBlogById(String id);
}
