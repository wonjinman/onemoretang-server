package com.deercorp.blackcompany.post;

import com.deercorp.blackcompany.BaseDateEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "subComments")
@NoArgsConstructor
public class SubCommentsEntity extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String content;
    private String createdBy;
    @Setter
    private LocalDateTime deletedAt;
    @ManyToOne
    private CommentEntity comment;

    public SubCommentsEntity(String content, String createdBy, CommentEntity comment) {
        this.content = content;
        this.createdBy = createdBy;
        this.comment = comment;
    }

}
