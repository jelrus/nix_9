package ua.com.alevel.view.controller.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.CommentFacade;
import ua.com.alevel.facade.PostFacade;
import ua.com.alevel.facade.StandardUserFacade;
import ua.com.alevel.view.controller.AbstractController;
import ua.com.alevel.view.dto.response.DtoResponse;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.form.CommentDtoResponse;
import ua.com.alevel.view.dto.response.form.PostDtoResponse;
import ua.com.alevel.view.dto.response.user.UserDtoResponse;

import java.util.List;

@Controller
@RequestMapping("open/posts")
public class PostController extends AbstractController {

    private final PostFacade postFacade;
    private final CommentFacade commentFacade;
    private final StandardUserFacade standardUserFacade;

    public PostController(PostFacade postFacade, CommentFacade commentFacade, StandardUserFacade standardUserFacade) {
        this.postFacade = postFacade;
        this.commentFacade = commentFacade;
        this.standardUserFacade = standardUserFacade;
    }

    @GetMapping
    private String allPosts(Model model) {
        List<PostDtoResponse> posts = postFacade.findAllPostsFiltered();
        model.addAttribute("allPosts", posts);
        model.addAttribute("allUsers", standardUserFacade);
        model.addAttribute("postFacade", postFacade);
        model.addAttribute("commentFacade", commentFacade);
        return "pages/open/posts";
    }

    @GetMapping("{postId}/comments")
    private String redirectToComments(Model model, @PathVariable Long postId, WebRequest webRequest){
        PostDtoResponse dto = postFacade.findById(postId);
        List<CommentDtoResponse> comments = commentFacade.findAllCommentsByPostId(postId);
        List<UserDtoResponse> users = standardUserFacade.findAll(webRequest).getItems();
        model.addAttribute("post", dto);
        model.addAttribute("comments", comments);
        model.addAttribute("allUsers", users);
        model.addAttribute("postFacade", postFacade);
        model.addAttribute("commentFacade", commentFacade);
        return "pages/open/comments";
    }
}
