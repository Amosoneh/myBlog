package africa.semicolon.myBlog.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Comment {
    @Id
    private String id;
    private String comment;
}
