package africa.semicolon.myBlog.services;

import africa.semicolon.myBlog.data.models.Comment;
import africa.semicolon.myBlog.dtos.requests.CommentRequest;
import africa.semicolon.myBlog.dtos.response.CommentResponse;

public interface CommentService {
    Comment createComment(CommentRequest request);
    CommentResponse deleteComment(CommentRequest request);
    void saveComment(Comment comment);
}
