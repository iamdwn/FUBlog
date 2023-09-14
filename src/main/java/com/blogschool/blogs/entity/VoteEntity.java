package com.blogschool.blogs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
@Table(name = "Votes")
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;


    @Column
    private Long voteValue; // 1 for upvote, -1 for downvote

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity userVote;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private BlogPostEntity postVote;

    public Long getVoteId() {
        return Id;
    }

    public Long getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(Long voteValue) {
        this.voteValue = voteValue;
    }

    public UserEntity getUserVote() {
        return userVote;
    }

    public void setUserVote(UserEntity userVote) {
        this.userVote = userVote;
    }

    public BlogPostEntity getPostVote() {
        return postVote;
    }

    public void setPostVote(BlogPostEntity postVote) {
        this.postVote = postVote;
    }

    public VoteEntity() {
    }

    public VoteEntity(Long voteValue, UserEntity userVote, BlogPostEntity postVote) {
        this.voteValue = voteValue;
        this.userVote = userVote;
        this.postVote = postVote;
    }
}