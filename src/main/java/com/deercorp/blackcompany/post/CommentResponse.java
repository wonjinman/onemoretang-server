package com.deercorp.blackcompany.post;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private List<SubCommentResponse> subComments;

    public CommentResponse(Long id, String content, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, LocalDateTime deletedAt, List<SubCommentResponse> subComments) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.subComments = subComments;
    }
}
