package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.request.form.PostDtoRequest;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.form.PostDtoResponse;
import ua.com.alevel.view.dto.response.user.UserDtoResponse;

import java.util.List;

public interface PostFacade extends BaseFacade<PostDtoRequest, PostDtoResponse> {

    List<PostDtoResponse> findAllPostsFiltered();

    List<PostDtoResponse> findAllPostsByUserId(Long userId);

    UserDtoResponse findUserByPostId(Long postId);
}
