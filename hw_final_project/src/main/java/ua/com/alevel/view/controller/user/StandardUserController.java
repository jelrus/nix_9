package ua.com.alevel.view.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.facade.CommentFacade;
import ua.com.alevel.facade.PostFacade;
import ua.com.alevel.facade.StandardUserFacade;
import ua.com.alevel.service.OpinionService;
import ua.com.alevel.view.controller.AbstractController;
import ua.com.alevel.view.dto.request.form.CommentDtoRequest;
import ua.com.alevel.view.dto.request.form.PostDtoRequest;
import ua.com.alevel.view.dto.request.user.UserDtoRequest;
import ua.com.alevel.view.dto.response.form.CommentDtoResponse;
import ua.com.alevel.view.dto.response.form.PostDtoResponse;
import ua.com.alevel.view.dto.response.user.UserDtoResponse;

import java.util.List;

@Validated
@Controller
@RequestMapping("/user")
public class StandardUserController extends AbstractController {

    private final StandardUserFacade standardUserFacade;
    private final PostFacade postFacade;
    private final CommentFacade commentFacade;
    private final OpinionService opinionService;

    public StandardUserController(StandardUserFacade standardUserFacade, PostFacade postFacade, CommentFacade commentFacade, OpinionService opinionService) {
        this.standardUserFacade = standardUserFacade;
        this.postFacade = postFacade;
        this.commentFacade = commentFacade;
        this.opinionService = opinionService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Long userId = standardUserFacade.findIdByUsername(getUsernameFromSecurityContext());
        UserDtoResponse userDto = standardUserFacade.findById(userId);
        model.addAttribute("user", userDto);
        return "redirect:/user/posts/personal";
    }

    @GetMapping("/open/posts")
    public String posts(Model model) {
        List<PostDtoResponse> posts = postFacade.findAllPostsFiltered();
        model.addAttribute("allPosts", posts);
        model.addAttribute("allUsers", standardUserFacade);
        model.addAttribute("postFacade", postFacade);
        model.addAttribute("commentFacade", commentFacade);
        model.addAttribute("opinionService", opinionService);
        return "pages/user/posts/post_all";
    }

    @GetMapping("/open/posts/{postId}/comments")
    public String comments(Model model, @PathVariable Long postId,
                           @ModelAttribute("commentDto") CommentDtoRequest comment) {
        PostDtoResponse postDtoResponse = postFacade.findById(postId);
        UserDtoResponse user = standardUserFacade.findById(standardUserFacade.findIdByUsername(getUsernameFromSecurityContext()));
        List<CommentDtoResponse> comments = commentFacade.findAllCommentsByPostId(postId);
        model.addAttribute("post", postDtoResponse);
        model.addAttribute("comments", comments);
        model.addAttribute("allUsers", standardUserFacade);
        model.addAttribute("postFacade", postFacade);
        model.addAttribute("commentFacade", commentFacade);
        model.addAttribute("user", user);
        model.addAttribute("opinionService", opinionService);
        return "pages/user/comments/comment_all";
    }

    @PostMapping("/open/posts/{postId}/comments")
    public String postComment(@PathVariable Long postId,
                              @ModelAttribute("comment") CommentDtoRequest comment) {
        standardUserFacade.createComment(getUsernameFromSecurityContext(), postId, comment);
        return "redirect:/user/open/posts/{postId}/comments";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        UserDtoResponse user = standardUserFacade.findById(standardUserFacade.findIdByUsername(getUsernameFromSecurityContext()));
        model.addAttribute("user", user);
        return "pages/user/profile_edit";
    }

    @PostMapping("/profile/edit")
    public String editProfileUpdate(@ModelAttribute("user") UserDtoRequest userRequest) {
        standardUserFacade.update(userRequest, standardUserFacade.findIdByUsername(getUsernameFromSecurityContext()));
        return "redirect:/user/posts/personal";
    }

    @GetMapping("/posts/personal")
    public String personalPosts(Model model) {
        UserDtoResponse user = standardUserFacade.findByUsername(getUsernameFromSecurityContext());
        List<PostDtoResponse> posts = postFacade.findAllPostsByUserId(user.getId());
        model.addAttribute("posts", posts);
        model.addAttribute("user", user);
        model.addAttribute("postFacade", postFacade);
        model.addAttribute("commentFacade", commentFacade);
        model.addAttribute("opinionService", opinionService);
        return "pages/user/posts/user_posts_all";
    }

    @GetMapping("/comments/personal")
    public String personalComments(Model model, @ModelAttribute("commentDto") CommentDtoRequest comment) {
        UserDtoResponse userDto = standardUserFacade.findByUsername(getUsernameFromSecurityContext());
        List<CommentDtoResponse> comments = commentFacade.findAllCommentsByUserId(userDto.getId());
        model.addAttribute("user", userDto);
        model.addAttribute("comments", comments);
        model.addAttribute("commentFacade", commentFacade);
        model.addAttribute("postFacade", postFacade);
        model.addAttribute("opinionService", opinionService);
        return "pages/user/comments/user_comments_all";
    }

    @GetMapping("/post/new")
    public String newPost(Model model, @ModelAttribute("postDto") PostDtoRequest post) {
        UserDtoResponse user = standardUserFacade.findById(standardUserFacade.findIdByUsername(getUsernameFromSecurityContext()));
        model.addAttribute("user", user);
        return "pages/user/posts/post_new";
    }

    @PostMapping("/post/create")
    public String createPost(@ModelAttribute("comment") PostDtoRequest post) {
        standardUserFacade.createPost(getUsernameFromSecurityContext(), post);
        return "redirect:/user/posts/personal";
    }

    @GetMapping("/post={postId}/edit")
    public String editPost(Model model, @PathVariable Long postId) {
        PostDtoResponse post = postFacade.findById(postId);
        model.addAttribute("post", post);
        return "pages/user/posts/post_edit";
    }

    @PostMapping("/post={postId}/edit")
    public String editPostRedirect(@ModelAttribute("post") PostDtoRequest post, @PathVariable Long postId) {
        postFacade.update(post, postId);
        return "redirect:/user/posts/personal";
    }

    @PostMapping("/comment={commentId}/edit")
    public String editCommentRedirect(@ModelAttribute("comment") CommentDtoRequest comment, @PathVariable Long commentId) {
        commentFacade.update(comment, commentId);
        return "redirect:/user/comments/personal";
    }

    @GetMapping("/comment={commentId}/delete")
    public String deleteComment(@PathVariable Long commentId) {
        standardUserFacade.deleteComment(getUsernameFromSecurityContext(),
                                         commentFacade.findById(commentId).getPostId(),
                                         commentId);
        return "redirect:/user/comments/personal";
    }

    @GetMapping("/post={postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        standardUserFacade.deletePost(getUsernameFromSecurityContext(), postId);
        return "redirect:/user/posts/personal";
    }

    @GetMapping("/profile/{userName}/posts")
    public String viewOtherProfilePosts(Model model, @PathVariable String userName) {
        UserDtoResponse user = standardUserFacade.findByUsername(userName);
        List<PostDtoResponse> posts = postFacade.findAllPostsByUserId(user.getId());
        model.addAttribute("posts", posts);
        model.addAttribute("user", user);
        model.addAttribute("commentFacade", commentFacade);
        model.addAttribute("postFacade", postFacade);
        model.addAttribute("opinionService", opinionService);
        return "pages/user/other_profile/user_posts";
    }

    @GetMapping("/profile/{userName}/comments")
    public String viewOtherProfileComments(Model model, @PathVariable String userName) {
        UserDtoResponse user = standardUserFacade.findByUsername(userName);
        List<CommentDtoResponse> comments = commentFacade.findAllCommentsByUserId(user.getId());
        model.addAttribute("comments", comments);
        model.addAttribute("user", user);
        model.addAttribute("commentFacade", commentFacade);
        model.addAttribute("postFacade", postFacade);
        model.addAttribute("opinionService", opinionService);
        return "pages/user/other_profile/user_comments";
    }

    @GetMapping ("/like/post={postId}")
    private String likePost(@PathVariable Long postId){
        opinionService.makePositiveOpinion(standardUserFacade.findIdByUsername(getUsernameFromSecurityContext()), postId);
        return "redirect:/user/open/posts#post{postId}";
    }

    @GetMapping("/dislike/post={postId}")
    private String dislikePost(@PathVariable Long postId){
        opinionService.makeNegativeOpinion(standardUserFacade.findIdByUsername(getUsernameFromSecurityContext()), postId);
        return "redirect:/user/open/posts#post{postId}";
    }
}