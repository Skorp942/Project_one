package com.learning.web.post;


import com.learning.dao.UserDao;
import com.learning.entity.Post;
import com.learning.entity.User;
import com.learning.post.PostRepository;
import com.learning.util.paginated.SimplePaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController {

    @Autowired private PostRepository postRepository;

    @ModelAttribute("PostController.command")
    public PostForm getCommand(){
        return new PostForm();
    }

    @Autowired
    private PostEditValidator postEditValidator;

    @RequestMapping("/blog.html")
    public String searchPost(
            Model model,
            @ModelAttribute("PostController.command") PostForm command
    ){
        SimplePaginatedList list = postRepository.getTitle(command);
        model.addAttribute("list", list);
        return "blog";
    }

    @RequestMapping(value = "/blog/add.html", method = RequestMethod.POST, params = "!_cancel")
    public String onPost(
            Model model,
            @ModelAttribute("command") Post command,
            BindingResult result){

        postEditValidator.validate(command, result);
        postRepository.saveOrUpdate(command);
        return "redirect:/blog/add.html?Id=" + command.getId();
    }
}
