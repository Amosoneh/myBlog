package africa.semicolon.myBlog.data.repositories;

import africa.semicolon.myBlog.data.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
    Comment findCommentById(String id);

}
