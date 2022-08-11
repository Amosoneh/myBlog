package africa.semicolon.myBlog.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LogInRequest {
    private String userName;
    private String password;
}
