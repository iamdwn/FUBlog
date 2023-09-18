package com.blogschool.blogs.service;

import com.blogschool.blogs.dto.CommentDTO;
import com.blogschool.blogs.entity.BlogPostEntity;
import com.blogschool.blogs.entity.CommentEntity;
import com.blogschool.blogs.model.ResponseObject;
import com.blogschool.blogs.entity.UserEntity;
import com.blogschool.blogs.repository.BlogPostRepository;
import com.blogschool.blogs.repository.CommentRepository;
import com.blogschool.blogs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogPostRepository blogPostRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository,
                          BlogPostRepository blogPostRepository,
                          UserRepository userRepository) {

        this.commentRepository = commentRepository;
        this.blogPostRepository = blogPostRepository;
        this.userRepository = userRepository;
    }


    public ResponseEntity<ResponseObject> viewComment(Long postId) {

        Optional<BlogPostEntity> blogPostEntity = blogPostRepository.findById(postId);

        if (blogPostEntity.isPresent()) {

            List<CommentEntity> list = commentRepository.findByPostComment(blogPostEntity.get());
            return list.size() > 0 ?
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseObject("ok", "comment of postId: " + postId, list)) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ResponseObject("failed", "no comment found of postId: " + postId, ""));
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "post doesn't exists", ""));
        }
    }

    public ResponseEntity<ResponseObject> insertComment(Long postId, CommentDTO commentDTO) {

        Optional<BlogPostEntity> blogPostEntity = blogPostRepository.findById(postId);
        Optional<UserEntity> userEntity = userRepository.findById(commentDTO.getUserId());

        if (blogPostEntity.isPresent()
                && userEntity.isPresent()) {

            UserEntity user = userEntity.get();

            Date createdDate = new Date();
            CommentEntity commentEntity = new CommentEntity(commentDTO.getContent(), createdDate,
                    user, blogPostEntity.get());

            commentRepository.save(commentEntity);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "comment have been inserted", commentEntity));
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("failed", "post or user doesn't exists", ""));
        }
    }


    public ResponseEntity<ResponseObject> updateComment(Long postId, CommentDTO commentDTO) {
        Optional<CommentEntity> commentEntity = commentRepository.findById(commentDTO.getCommentId());
        if (commentEntity.isPresent()) {
            CommentEntity updatedComment = commentEntity.get();
            updatedComment.setContent(commentDTO.getContent());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "updated successfully", commentRepository.save(updatedComment)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("failed", "comment does not exists", ""));
        }
    }

}