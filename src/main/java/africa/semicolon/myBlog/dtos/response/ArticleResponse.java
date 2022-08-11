package africa.semicolon.myBlog.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class ArticleResponse {
    private String message;
}
