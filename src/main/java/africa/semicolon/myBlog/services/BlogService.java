package africa.semicolon.myBlog.services;

import africa.semicolon.myBlog.data.models.Blog;
import africa.semicolon.myBlog.dtos.requests.BlogRequest;
import africa.semicolon.myBlog.dtos.response.BlogResponse;

public interface BlogService {
    Blog createBlog(BlogRequest request);

    void save(Blog blog);

}
