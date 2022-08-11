package africa.semicolon.myBlog.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUserRequest {
    private String userName;
    private String password;
    private String id;
}
