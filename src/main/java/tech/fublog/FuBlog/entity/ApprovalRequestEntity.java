package tech.fublog.FuBlog.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Data
@NoArgsConstructor
@Table(name = "ApprovalRequest")
public class ApprovalRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private boolean isApproved;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private UserEntity request;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private UserEntity review;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private BlogPostEntity blogPost;

    public ApprovalRequestEntity(BlogPostEntity blogPost) {
        this.request = blogPost.getAuthors();
        this.blogPost = blogPost;
    }

    public ApprovalRequestEntity(Long id, boolean isApproved, UserEntity request, UserEntity review, BlogPostEntity blogPost) {
        Id = id;
        this.isApproved = isApproved;
        this.request = request;
        this.review = review;
        this.blogPost = blogPost;
    }
}
