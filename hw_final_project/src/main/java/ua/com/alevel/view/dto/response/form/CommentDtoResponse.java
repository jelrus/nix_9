package ua.com.alevel.view.dto.response.form;

import ua.com.alevel.persistence.entity.form.Comment;
import ua.com.alevel.view.dto.response.DtoResponse;

import java.util.Objects;

public class CommentDtoResponse extends DtoResponse {

    private String body;
    private Boolean visible;
    private Long postId;
    private Long userId;

    public CommentDtoResponse(Comment comment){
        super(comment.getId(), comment.getCreated(), comment.getUpdated(), comment.getVisible());
        setBody(comment.getBody());
        setPostId(comment.getPost().getId());
        setUserId(comment.getUser().getId());
        setVisible(comment.getVisible());
    }

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
    public Boolean getVisible() {
        return visible;
    }

    @Override
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDtoResponse that = (CommentDtoResponse) o;
        return Objects.equals(body, that.body) && Objects.equals(visible, that.visible) && Objects.equals(postId, that.postId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, visible, postId, userId);
    }

    @Override
    public String toString() {
        return "CommentDtoResponse{" +
                "body='" + body + '\'' +
                ", visible=" + visible +
                ", postId=" + postId +
                ", userId=" + userId +
                '}';
    }
}
