package tech.fublog.FuBlog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BlogPost")
public class BlogPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String typePost;

    @Column
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Column
    @CreatedDate
    private Date createdDate;


    @Column
    @LastModifiedDate
    private Date modifiedDate;


    @Column
    private Long approvedBy;

    @Column
    private  Boolean status;

    @Column
    private Boolean isApproved;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "author_id")
    private UserEntity authors;

//    @ManyToOne
//    @JoinColumn(name = "authorModified_id")
//    private UserEntity authorsModified;

    @OneToMany(mappedBy = "postVote")
    private Set<VoteEntity> votes = new HashSet<>();

    @OneToMany(mappedBy = "blogPost")
    @JsonIgnore
    private Set<ApprovalRequestEntity> approvalRequests = new HashSet<>();

    @OneToMany(mappedBy = "postComment")
    @JsonIgnore
    private Set<CommentEntity> postComments = new HashSet<>();

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private Set<PostTagEntity> postTags = new HashSet<>();

//    @ManyToMany(mappedBy = "blogPosts")
//    private Set<TagEntity> tags = new HashSet<>();


    public BlogPostEntity(String typePost, String title, String content, Date createdDate, Date modifiedDate,
                          Long approvedBy, Boolean status, Boolean isApproved,
                          CategoryEntity category, UserEntity authors) {
        this.typePost = typePost;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.approvedBy = approvedBy;
        this.status = status;
        this.isApproved = isApproved;
        this.category = category;
        this.authors = authors;
    }
}
