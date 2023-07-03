package com.deercorp.blackcompany.post;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private List<CommentResponse> comments;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public PostResponse(Long id, String title, String content, List<CommentResponse> comments, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = comments;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }
}
