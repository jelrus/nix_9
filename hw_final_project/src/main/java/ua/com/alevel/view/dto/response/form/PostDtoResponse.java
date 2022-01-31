package ua.com.alevel.view.dto.response.form;

import ua.com.alevel.persistence.entity.form.Comment;
import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.view.dto.response.DtoResponse;

import java.util.List;
import java.util.Objects;

public class PostDtoResponse extends DtoResponse {

    private String headerPic;
    private String title;
    private String body;
    private Boolean visible;
    private Long likes;
    private Long dislikes;
    private Long rating;
    private Long userId;
    private List<Comment> comments;

    public PostDtoResponse(Post post) {
        super(post.getId(), post.getCreated(), post.getUpdated(), post.getVisible());
        setHeaderPic(post.getHeaderPic());
        setTitle(post.getTitle());
        setBody(post.getBody());
        setVisible(post.getVisible());
        setLikes(post.getLikes());
        setDislikes(post.getDislikes());
        setRating(post.getRating());
        setUserId(post.getUser().getId());
        setComments(post.getComments().stream().toList());
    }

    public String getHeaderPic() {
        return headerPic;
    }

    public void setHeaderPic(String headerPic) {
        this.headerPic = headerPic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public Boolean getVisible() {
        return visible;
    }

    @Override
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDislikes() {
        return dislikes;
    }

    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDtoResponse that = (PostDtoResponse) o;
        return Objects.equals(headerPic, that.headerPic) &&
               Objects.equals(title, that.title) &&
               Objects.equals(body, that.body) &&
               Objects.equals(likes, that.likes) &&
               Objects.equals(dislikes, that.dislikes) &&
               Objects.equals(rating, that.rating) &&
               Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headerPic, title, body, likes, dislikes, rating, userId);
    }

    @Override
    public String toString() {
        return "PostDtoResponse{" +
                "headerPic='" + headerPic + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", rating=" + rating +
                ", userId=" + userId + "}";
    }
}
