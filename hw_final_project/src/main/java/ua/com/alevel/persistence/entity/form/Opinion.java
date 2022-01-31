package ua.com.alevel.persistence.entity.form;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.form.type.OpinionType;
import ua.com.alevel.persistence.entity.user.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "opinion")
public class Opinion extends BaseEntity {

    @Column(name = "userId")
    private Long userId;

    @Column(name = "postId")
    private Long postId;

    @Enumerated(EnumType.STRING)
    @Column(name = "opinion")
    private OpinionType opinionType;

    public Opinion() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public OpinionType getOpinionType() {
        return opinionType;
    }

    public void setOpinionType(OpinionType opinionType) {
        this.opinionType = opinionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opinion opinion = (Opinion) o;
        return Objects.equals(userId, opinion.userId) && Objects.equals(postId, opinion.postId) && opinionType == opinion.opinionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId, opinionType);
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "userId=" + userId +
                ", postId=" + postId +
                ", opinionType=" + opinionType +
                '}';
    }
}
