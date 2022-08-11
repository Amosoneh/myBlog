package africa.semicolon.myBlog.services;

import africa.semicolon.myBlog.data.models.Article;
import africa.semicolon.myBlog.data.models.User;
import africa.semicolon.myBlog.data.repositories.UserRepository;
import africa.semicolon.myBlog.dtos.requests.*;
import africa.semicolon.myBlog.dtos.response.*;
import africa.semicolon.myBlog.exceptions.ArticleNotFoundException;
import africa.semicolon.myBlog.exceptions.UserExistException;
import africa.semicolon.myBlog.exceptions.UserNotFoundException;
import africa.semicolon.myBlog.utils.ArticleAndResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        var user = userRepository.findUserByEmail(request.getUserName());
        if (user != null){
            throw new UserExistException(request.getUserName() + " already exist");
        }
        user = new User();
        user.setEmail(request.getUserName());
        user.setPassword(request.getPassword());
        userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage(String.format("%s successfully registered", request.getUserName()));
        return response;
    }

    @Override
    public User logInUser(LogInRequest request) {
        var user = userRepository.findUserByEmailAndPassword(request.getUserName(), request.getPassword());
        if (user == null){
            throw new UserNotFoundException(request.getUserName() + " not found. Incorrect username or password");
        }
        LogInResponse response = new LogInResponse();
        response.setMessage("Log in successful\nWelcome "+ user.getEmail());
        return user;
    }

    @Override
    public String getUserName(String userName) {
        var users = userRepository.findAll();
        for (var user: users){
            if (user.getEmail().equalsIgnoreCase(userName)){
                return user.getEmail();
            }
        }
        return null;
    }

    @Override
    public List<Article> viewUserArticle(ArticleRequest request) {
        var user = userRepository.findUserById(request.getUserId());
        return user.getBlog().getArticles();
    }

    @Override
    public ArticleResponse deleteArticle(ArticleRequest request) {
        var user = userRepository.findUserById(request.getUserId());
        user.getBlog().getArticles().removeIf(article -> article.getId().equals(request.getId()));
        articleService.saveAllArticle(user.getBlog().getArticles());
        blogService.save(user.getBlog());
        userRepository.save(user);
        return articleService.deleteArticle(request);
    }


    @Override
    public User findUserById(String id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    public Article getArticle(ArticleRequest request){
        var user = userRepository.findUserById(request.getUserId());
        for (var article: user.getBlog().getArticles()){
            if (article.getId().equals(request.getId())) return article;
        }
        throw new ArticleNotFoundException("Article with id "+request.getId()+ " not found");
    }

    @Override
    public BlogResponse createBlog(BlogRequest blogRequest) {
        var blog = blogService.createBlog(blogRequest);
        var user = findUserById(blogRequest.getUserId());
        if (user == null) throw new UserNotFoundException("User not found");
        user.setBlog(blog);
        userRepository.save(user);
        BlogResponse blogResponse = new BlogResponse();
        blogResponse.setMessage("Blog created successfully");
        return blogResponse;
    }

    @Override
    public ArticleAndResponse createArticle(ArticleRequest request) {
        var article = articleService.createArticle(request);
        var user = findUserById(request.getUserId());
        user.getBlog().getArticles().add(article.getArticle());
        blogService.save(user.getBlog());
        userRepository.save(user);
        ArticleResponse response = new ArticleResponse();
        response.setMessage("Article created successfully");
        return article;
    }

    @Override
    public CommentResponse addComment(CommentRequest request) {
        ArticleRequest request1 = new ArticleRequest();
        request1.setId(request.getArticleId());
        var articles = articleService.getArticle(request1);
         var saveComment = commentService.createComment(request);
        articles.getComments().add(saveComment);
        articleService.saveArticle(articles);
        RegisterUserRequest userRequest = new RegisterUserRequest();
        CommentResponse response = new CommentResponse();
        response.setMessage("comment added successfully");
        return response;
    }


}
