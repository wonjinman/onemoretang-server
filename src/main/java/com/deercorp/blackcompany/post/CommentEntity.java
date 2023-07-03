package com.deercorp.blackcompany.post;

import com.deercorp.blackcompany.BaseDateEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor
public class CommentEntity extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String createdBy;
    private LocalDateTime deletedAt;
    @ManyToOne
    private PostEntity post;
//    private Long postId;

    public CommentEntity(String content, String createdBy, PostEntity post) {
        this.content = content;
        this.createdBy = createdBy;
        this.post = post;
    }
}
