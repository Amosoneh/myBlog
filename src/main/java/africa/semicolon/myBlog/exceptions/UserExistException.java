package africa.semicolon.myBlog.exceptions;

public class UserExistException extends RuntimeException{
    public UserExistException(String message){
        super(message);
    }
}
