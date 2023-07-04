package com.deercorp.blackcompany.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCommentRepository extends JpaRepository<SubCommentsEntity, Long> {
    List<SubCommentsEntity> findAllByCommentId(Long commentId);
}
