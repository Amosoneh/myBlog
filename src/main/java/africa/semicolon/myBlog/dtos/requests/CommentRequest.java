package africa.semicolon.myBlog.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentRequest {
    private String id;
    private String comment;
    private String articleId;
    private String userId;
}
