package com.blogschool.blogs.controller;

import com.blogschool.blogs.dto.ApprovalRequestDTO;
import com.blogschool.blogs.entity.ResponseObject;
import com.blogschool.blogs.service.ApprovalRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blogPosts/approve")
public class ApprovalRequestController {
    private final ApprovalRequestService approvalRequestService;

    @Autowired
    public ApprovalRequestController(ApprovalRequestService approvalRequestService) {
        this.approvalRequestService = approvalRequestService;
    }

    @RequestMapping("/view")
    public ResponseEntity<ResponseObject> getAllRequest() {
        List<ApprovalRequestDTO> dtoList = approvalRequestService.getAllApprovalRequest()/*viewComment(postId)*/;
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("ok", "found", dtoList));
    }
}