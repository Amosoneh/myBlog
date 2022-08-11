package africa.semicolon.myBlog.data.repositories;

import africa.semicolon.myBlog.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByEmailAndPassword(String email, String password);
    User findUserByEmail(String userName);

    User findUserById(String id);

}
