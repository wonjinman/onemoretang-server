package com.deercorp.blackcompany.post;

import com.deercorp.blackcompany.BaseDateEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(length = 1000)
    private String content;
    private String createdBy;
    @Setter
    private LocalDateTime deletedAt;
    @ManyToOne
    private PostEntity post;

    public CommentEntity(String content, String createdBy, PostEntity post) {
        this.content = content;
        this.createdBy = createdBy;
        this.post = post;
    }
}
