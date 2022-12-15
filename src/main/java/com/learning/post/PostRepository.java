package com.learning.post;

import com.learning.entity.Post;
import com.learning.entity.User;
import com.learning.util.paginated.SimplePaginatedList;
import com.learning.web.post.PostForm;


public interface PostRepository  {

    SimplePaginatedList getTitle(PostForm form);

    void saveOrUpdate(Post post);

}

