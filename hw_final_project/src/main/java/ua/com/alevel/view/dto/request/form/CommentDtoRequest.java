package ua.com.alevel.view.dto.request.form;

import ua.com.alevel.view.dto.request.DtoRequest;

import java.util.Objects;

public class CommentDtoRequest extends DtoRequest {

    private String body;
    private Long postId;
    private Long userId;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDtoRequest that = (CommentDtoRequest) o;
        return Objects.equals(body, that.body) &&
               Objects.equals(postId, that.postId) &&
               Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, postId, userId);
    }

    @Override
    public String toString() {
        return "CommentDtoRequest{" +
                "body='" + body + '\'' +
                ", postId=" + postId +
                ", userId=" + userId +
                '}';
    }
}
