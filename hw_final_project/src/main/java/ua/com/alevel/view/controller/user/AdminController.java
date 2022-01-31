package ua.com.alevel.view.controller.user;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.AdminFacade;
import ua.com.alevel.facade.CommentFacade;
import ua.com.alevel.facade.PostFacade;
import ua.com.alevel.service.OpinionService;
import ua.com.alevel.view.controller.AbstractController;
import ua.com.alevel.view.dto.request.form.CommentDtoRequest;
import ua.com.alevel.view.dto.request.form.PostDtoRequest;
import ua.com.alevel.view.dto.request.user.UserDtoRequest;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.form.CommentDtoResponse;
import ua.com.alevel.view.dto.response.form.PostDtoResponse;
import ua.com.alevel.view.dto.response.user.UserDtoResponse;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    private final AdminFacade adminFacade;
    private final PostFacade postFacade;
    private final CommentFacade commentFacade;
    private final OpinionService opinionService;

    public AdminController(AdminFacade adminFacade,
                           PostFacade postFacade,
                           CommentFacade commentFacade,
                           OpinionService opinionService) {
        this.adminFacade = adminFacade;
        this.postFacade = postFacade;
        this.commentFacade = commentFacade;
        this.opinionService = opinionService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Long userId = adminFacade.findIdByUsername(getUsernameFromSecurityContext());
        UserDtoResponse userDto = adminFacade.findById(userId);
        model.addAttribute("user", userDto);
        return "redirect:/admin/posts/personal";
    }

    @GetMapping("/open/posts")
    public String posts(Model model) {
        List<PostDtoResponse> posts = postFacade.findAllPostsFiltered();
        model.addAttribute("allPosts", posts);
        model.addAttribute("allUsers", adminFacade);
        model.addAttribute("commentFacade", commentFacade);
        model.addAttribute("opinionService", opinionService);
        return "pages/admin/posts/post_all";
    }

    @GetMapping("/open/posts/{postId}/comments")
    public String comments(Model model, @PathVariable Long postId,
                           @ModelAttribute("commentDto") CommentDtoRequest commentDto) {
        PostDtoResponse postDtoResponse = postFacade.findById(postId);
        UserDtoResponse user = adminFacade.findById(adminFacade.findIdByUsername(getUsernameFromSecurityContext()));
        List<CommentDtoResponse> comments = commentFacade.findAllCommentsByPostId(postId);
        System.out.println(comments);
        model.addAttribute("post", postDtoResponse);
        model.addAttribute("comments", comments);
        model.addAttribute("allUsers", adminFacade);
        model.addAttribute("commentFacade", commentFacade);
        model.addAttribute("user", user);
        model.addAttribute("opinionService", opinionService);
        return "pages/admin/comments/comment_all";
    }

    @PostMapping("/open/posts/{postId}/comments")
    public String postComment(@PathVariable Long postId,
                              @ModelAttribute("comment") CommentDtoRequest comment) {
        adminFacade.createComment(getUsernameFromSecurityContext(), postId, comment);
        return "redirect:/admin/open/posts/{postId}/comments";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        UserDtoResponse user = adminFacade.findById(adminFacade.findIdByUsername(getUsernameFromSecurityContext()));
        model.addAttribute("user", user);
        return "pages/admin/profile_edit";
    }

    @PostMapping("/profile/edit")
    public String editProfileUpdate(@ModelAttribute("user") UserDtoRequest userRequest) {
        adminFacade.update(userRequest, adminFacade.findIdByUsername(getUsernameFromSecurityContext()));
        return "redirect:/admin/posts/personal";
    }

    @GetMapping("/posts/personal")
    public String personalPosts(Model model) {
        UserDtoResponse user = adminFacade.findByUsername(getUsernameFromSecurityContext());
        List<PostDtoResponse> posts = postFacade.findAllPostsByUserId(user.getId());
        model.addAttribute("posts", posts);
        model.addAttribute("user", user);
        model.addAttribute("postFacade", postFacade);
        model.addAttribute("commentFacade", commentFacade);
        model.addAttribute("opinionService", opinionService);
        return "pages/admin/posts/user_posts_all";
    }

    @GetMapping("/comments/personal")
    public String personalComments(Model model, @ModelAttribute("commentDto") CommentDtoRequest comment) {
        UserDtoResponse userDto = adminFacade.findByUsername(getUsernameFromSecurityContext());
        List<CommentDtoResponse> comments = commentFacade.findAllCommentsByUserId(userDto.getId());
        model.addAttribute("user", userDto);
        model.addAttribute("comments", comments);
        model.addAttribute("commentFacade", commentFacade);
        model.addAttribute("postFacade", postFacade);
        model.addAttribute("opinionService", opinionService);
        return "pages/admin/comments/user_comments_all";
    }

    @GetMapping("/post/new")
    public String newPost(Model model, @ModelAttribute("postDto") PostDtoRequest post) {
        UserDtoResponse user = adminFacade.findById(adminFacade.findIdByUsername(getUsernameFromSecurityContext()));
        model.addAttribute("user", user);
        return "pages/admin/posts/post_new";
    }

    @PostMapping("/post/create")
    public String createPost(@ModelAttribute("comment") PostDtoRequest post) {
        adminFacade.createPost(getUsernameFromSecurityContext(), post);
        return "redirect:/admin/posts/personal";
    }

    @GetMapping("/post={postId}/edit")
    public String editPost(Model model, @PathVariable Long postId) {
        PostDtoResponse post = postFacade.findById(postId);
        model.addAttribute("post", post);
        return "pages/admin/posts/post_edit";
    }

    @PostMapping("/post={postId}/edit")
    public String editPostRedirect(@ModelAttribute("post") PostDtoRequest post, @PathVariable Long postId) {
        postFacade.update(post, postId);
        return "redirect:/admin/posts/personal";
    }

    @PostMapping("/comment={commentId}/edit")
    public String editCommentRedirect(@ModelAttribute("comment") CommentDtoRequest comment, @PathVariable Long commentId) {
        commentFacade.update(comment, commentId);
        return "redirect:/admin/comments/personal";
    }

    @GetMapping("/comment={commentId}/delete")
    public String deleteComment(@PathVariable Long commentId) {
        adminFacade.deleteComment(getUsernameFromSecurityContext(), commentFacade.findById(commentId).getPostId(), commentId);
        return "redirect:/admin/comments/personal";
    }

    @GetMapping("/post={postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        adminFacade.deletePost(getUsernameFromSecurityContext(), postId);
        return "redirect:/admin/posts/personal";
    }

    @GetMapping("/profile/{userName}/posts")
    public String viewOtherProfilePosts(Model model, @PathVariable String userName, WebRequest request) {
        UserDtoResponse user = adminFacade.findByUsername(userName);
        List<PostDtoResponse> posts = postFacade.findAllPostsByUserId(user.getId());
        model.addAttribute("posts", posts);
        model.addAttribute("user", user);
        model.addAttribute("commentFacade", commentFacade);
        model.addAttribute("opinionService", opinionService);
        return "pages/admin/other_profile/user_posts";
    }

    @GetMapping("/profile/{userName}/comments")
    public String viewOtherProfileComments(Model model, @PathVariable String userName, WebRequest request) {
        UserDtoResponse user = adminFacade.findByUsername(userName);
        List<CommentDtoResponse> comments = commentFacade.findAllCommentsByUserId(user.getId());
        model.addAttribute("comments", comments);
        model.addAttribute("user", user);
        model.addAttribute("commentFacade", commentFacade);
        model.addAttribute("postFacade", postFacade);
        model.addAttribute("opinionService", opinionService);
        return "pages/admin/other_profile/user_comments";
    }

    @GetMapping("/profile/manage/users")
    public String manageUsers(Model model, WebRequest webRequest){
        UserDtoResponse current = adminFacade.findByUsername(getUsernameFromSecurityContext());
        AbstractController.HeaderName[] columnNames = getColumnNamesForUsers();
        PageData<UserDtoResponse> response = adminFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/admin/profile/manage/users");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All users");
        model.addAttribute("allowCreate", true);
        model.addAttribute("current", current);
        return "pages/admin/managing/users_datatable";
    }

    @PostMapping("/profile/manage/users")
    public ModelAndView findAllUsersRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/admin/profile/manage/users", model);
    }

    private AbstractController.HeaderName[] getColumnNamesForUsers() {
        return new AbstractController.HeaderName[]{
                new AbstractController.HeaderName("#", null, null),
                new AbstractController.HeaderName("Created", "created", "created"),
                new AbstractController.HeaderName("Updated", "updated", "updated"),
                new AbstractController.HeaderName("Profile pic", "profile_pic", "profilePic"),
                new AbstractController.HeaderName("Username", "username", "username"),
                new AbstractController.HeaderName("Details", null, null),
                new AbstractController.HeaderName("Disable", null, null)
        };
    }

    @GetMapping("/profile/manage/disable/{username}")
    public String disableUser(@PathVariable String username){
        adminFacade.disableUser(adminFacade.findIdByUsername(username));
        return "redirect:/admin/profile/manage/users";
    }

    @GetMapping("/profile/manage/posts")
    public String managePosts(Model model, WebRequest webRequest){
        AbstractController.HeaderName[] columnNames = getColumnNamesForPosts();
        PageData<PostDtoResponse> response = postFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/admin/profile/manage/posts");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All posts");
        model.addAttribute("allowCreate", true);
        model.addAttribute("allUsers", adminFacade);
        return "pages/admin/managing/posts_datatable";
    }

    @PostMapping("/profile/manage/posts")
    public ModelAndView findAllPostsRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/admin/profile/manage/posts", model);
    }

    private AbstractController.HeaderName[] getColumnNamesForPosts() {
        return new AbstractController.HeaderName[]{
                new AbstractController.HeaderName("#", null, null),
                new AbstractController.HeaderName("Created", "created", "created"),
                new AbstractController.HeaderName("Updated", "updated", "updated"),
                new AbstractController.HeaderName("Header pic", "header_pic", "headerPic"),
                new AbstractController.HeaderName("Title", "title", "title"),
                new AbstractController.HeaderName("Body", "body", "body"),
                new AbstractController.HeaderName("Owner", null, null),
                new AbstractController.HeaderName("Hide", null, null)
        };
    }

    @GetMapping("/profile/manage/hide/post={postId}")
    public String hidePost(@PathVariable Long postId){
        adminFacade.disablePost(postId);
        return "redirect:/admin/profile/manage/posts";
    }

    @GetMapping("/profile/manage/comments")
    public String manageComments(Model model, WebRequest webRequest){
        AbstractController.HeaderName[] columnNames = getColumnNamesForComments();
        PageData<CommentDtoResponse> response = commentFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/admin/profile/manage/comments");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All comments");
        model.addAttribute("allowCreate", true);
        model.addAttribute("allUsers", adminFacade);
        return "pages/admin/managing/comments_datatable";
    }

    @PostMapping("/profile/manage/comments")
    public ModelAndView findAllCommentsRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/admin/profile/manage/comments", model);
    }

    private AbstractController.HeaderName[] getColumnNamesForComments() {
        return new AbstractController.HeaderName[]{
                new AbstractController.HeaderName("#", null, null),
                new AbstractController.HeaderName("Created", "created", "created"),
                new AbstractController.HeaderName("Updated", "updated", "updated"),
                new AbstractController.HeaderName("Body", "body", "body"),
                new AbstractController.HeaderName("Owner", null, null),
                new AbstractController.HeaderName("Hide", null, null)
        };
    }

    @GetMapping("/profile/manage/hide/comment={commentId}")
    public String hideComment(@PathVariable Long commentId){
        adminFacade.disableComment(commentId);
        return "redirect:/admin/profile/manage/comments";
    }

    @GetMapping ("/like/post={postId}")
    private String likePost(@PathVariable Long postId){
        opinionService.makePositiveOpinion(adminFacade.findIdByUsername(getUsernameFromSecurityContext()), postId);
        return "redirect:/admin/open/posts#post{postId}";
    }

    @GetMapping("/dislike/post={postId}")
    private String dislikePost(@PathVariable Long postId){
        opinionService.makeNegativeOpinion(adminFacade.findIdByUsername(getUsernameFromSecurityContext()), postId);
        return "redirect:/admin/open/posts#post{postId}";
    }
}