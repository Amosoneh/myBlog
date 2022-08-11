package africa.semicolon.myBlog.exceptions;

public class BlogExistException extends RuntimeException {
    public BlogExistException(String message) {
        super(message);
    }
}
