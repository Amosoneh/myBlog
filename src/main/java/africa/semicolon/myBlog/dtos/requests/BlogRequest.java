package africa.semicolon.myBlog.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@NoArgsConstructor
public class BlogRequest {
    @Id
    private String id;
    private String userId;
    private String name;


}
