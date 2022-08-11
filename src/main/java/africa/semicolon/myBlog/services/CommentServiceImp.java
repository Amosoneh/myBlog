package africa.semicolon.myBlog.services;

import africa.semicolon.myBlog.data.models.Comment;
import africa.semicolon.myBlog.data.repositories.CommentRepository;
import africa.semicolon.myBlog.dtos.requests.CommentRequest;
import africa.semicolon.myBlog.dtos.response.CommentResponse;
import africa.semicolon.myBlog.exceptions.CommentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public Comment createComment(CommentRequest request) {
        Comment comment = new Comment();
        comment.setComment(request.getComment());
        commentRepository.save(comment);
        CommentResponse response = new CommentResponse();
        response.setMessage("Comment added");
        return comment;
    }

    @Override
    public CommentResponse deleteComment(CommentRequest request) {
        var comment = commentRepository.findCommentById(request.getId());
        if (comment == null) throw new CommentNotFoundException("comment not found or might be deleted");
        else{
            commentRepository.delete(comment);
        }
        CommentResponse response = new CommentResponse();
        response.setMessage("comment deleted successfully");
        return response;
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
}
