package ua.com.alevel.view.dto.response.user;

import ua.com.alevel.persistence.entity.form.Comment;
import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.view.dto.response.DtoResponse;

import java.util.Date;
import java.util.List;

public class UserDtoResponse extends DtoResponse {

    private String username;
    private String firstName;
    private String lastName;
    private String description;
    private String profilePic;
    private Boolean enabled;
    private List<Post> posts;
    private Long likes;
    private Long dislikes;
    private Long rating;
    private List<Comment> comments;

    public UserDtoResponse(User user) {
        super(user.getId(), user.getCreated(), user.getUpdated(), user.getVisible());
        setUsername(user.getUsername());
        setFirstName(user.getFirstName());
        setDescription(user.getDescription());
        setLastName(user.getLastName());
        setProfilePic(user.getProfilePic());
        setEnabled(user.getEnabled());
        setPosts(user.getPosts().stream().toList());
        setComments(user.getComments().stream().toList());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
