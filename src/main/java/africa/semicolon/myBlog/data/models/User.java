package africa.semicolon.myBlog.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("USERS")
@Data
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    @DBRef
    private Blog blog;
}
