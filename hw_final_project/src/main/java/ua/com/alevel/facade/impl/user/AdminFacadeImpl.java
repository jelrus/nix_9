package ua.com.alevel.facade.impl.user;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.AdminFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.form.Comment;
import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.service.AdminService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.facades.Converter;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.request.form.CommentDtoRequest;
import ua.com.alevel.view.dto.request.form.PostDtoRequest;
import ua.com.alevel.view.dto.request.user.UserDtoRequest;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.user.UserDtoResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminFacadeImpl implements AdminFacade {

    private final AdminService adminService;

    public AdminFacadeImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void create(UserDtoRequest adminDtoRequest) {
        User admin = new User();
        admin.setUsername(admin.getUsername());
        admin.setProfilePic(adminDtoRequest.getProfilePic());
        admin.setDescription(adminDtoRequest.getDescription());
        admin.setFirstName(adminDtoRequest.getFirstName());
        admin.setLastName(adminDtoRequest.getLastName());
        adminService.create(admin);
    }

    @Override
    public void update(UserDtoRequest adminDtoRequest, Long id) {
        User admin = adminService.findById(id).get();
        admin.setUsername(admin.getUsername());
        admin.setProfilePic(adminDtoRequest.getProfilePic());
        admin.setDescription(adminDtoRequest.getDescription());
        admin.setFirstName(adminDtoRequest.getFirstName());
        admin.setLastName(adminDtoRequest.getLastName());
        adminService.update(admin);
    }

    @Override
    public void delete(Long id) {
        adminService.delete(id);
    }

    @Override
    public UserDtoResponse findById(Long id) {
        return new UserDtoResponse(adminService.findById(id).get());
    }

    @Override
    public PageData<UserDtoResponse> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = Converter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<User> admins = adminService.findAll(dataTableRequest);
        List<UserDtoResponse> responseList = toDtoList(admins);
        PageData<UserDtoResponse> pageData = Converter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(admins.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public Long findIdByUsername(String username) {
        return adminService.getIdByUsername(username);
    }

    @Override
    public UserDtoResponse findByUsername(String username) {
        return new UserDtoResponse(adminService.getUserByUsername(username));
    }

    @Override
    public String findUsernameById(Long id) {
        return adminService.findById(id).get().getUsername();
    }

    @Override
    public void createPost(String username, PostDtoRequest postDtoRequest) {
        Post post = new Post();
        post.setHeaderPic(postDtoRequest.getHeaderPic());
        post.setTitle(postDtoRequest.getTitle());
        post.setBody(postDtoRequest.getBody());
        post.setLikes(0L);
        post.setDislikes(0L);
        post.setRating(0L);
        post.setComments(postDtoRequest.getComments());
        adminService.createPost(username, post);
    }

    @Override
    public void deletePost(String username, Long postId) {
        adminService.deletePost(username, postId);
    }

    @Override
    public void createComment(String username, Long postId, CommentDtoRequest commentDtoRequest) {
        Comment comment = new Comment();
        comment.setBody(commentDtoRequest.getBody());
        adminService.createComment(username, postId, comment);
    }

    @Override
    public void deleteComment(String username, Long postId, Long commentId) {
        adminService.deleteComment(username, postId, commentId);
    }

    @Override
    public void disablePost(Long postId) {
        adminService.disablePost(postId);
    }

    @Override
    public void disableComment(Long commentId) {
        adminService.disableComment(commentId);
    }

    @Override
    public void disableUser(Long userId) {
        adminService.disableUser(userId);
    }

    @Override
    public Integer getGlobalPosts() {
        return adminService.getGlobalPosts();
    }

    @Override
    public Integer getGlobalComments() {
        return adminService.getGlobalComments();
    }

    @Override
    public Integer getGlobalLikes() {
        return adminService.getGlobalLikes();
    }

    @Override
    public Integer getGlobalDislikes() {
        return adminService.getGlobalDislikes();
    }

    private List<UserDtoResponse> toDtoList(DataTableResponse<User> users) {
        return users.getItems()
                    .stream()
                    .map(UserDtoResponse::new)
                    .collect(Collectors.toList());
    }
}
