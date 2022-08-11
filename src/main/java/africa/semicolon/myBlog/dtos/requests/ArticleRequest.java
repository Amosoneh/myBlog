package africa.semicolon.myBlog.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class ArticleRequest {
    private String userId;
    private String id;
    private String title;
    private String body;

}
