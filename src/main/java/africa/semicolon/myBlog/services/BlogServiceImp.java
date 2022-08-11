package africa.semicolon.myBlog.services;

import africa.semicolon.myBlog.data.models.Blog;
import africa.semicolon.myBlog.data.repositories.BlogRepository;
import africa.semicolon.myBlog.dtos.requests.BlogRequest;
import africa.semicolon.myBlog.dtos.response.BlogResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImp implements BlogService{


    @Autowired
    private BlogRepository blogRepository;
    @Override
    public Blog createBlog(BlogRequest request) {
        var blog = new Blog();
        blog.setName(request.getName());
        BlogResponse response = new BlogResponse();
        response.setMessage("Blog created successfully");
        return blogRepository.save(blog);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }
}
