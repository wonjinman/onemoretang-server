package com.deercorp.blackcompany.post;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SubCommentResponse {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public SubCommentResponse(Long id, String content, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }
}
