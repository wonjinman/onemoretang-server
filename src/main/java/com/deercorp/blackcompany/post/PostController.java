package com.deercorp.blackcompany.post;

import com.deercorp.blackcompany.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final SubCommentRepository subCommentsRepository;

    @GetMapping("/posts")
    public CommonResponse<List<PostEntity>> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<PostEntity> postPage = postRepository.findAll(pageable);

        return CommonResponse.ok(postPage.getContent());
    }

    @GetMapping("/posts/{id}")
    public CommonResponse<PostResponse> getPost(@PathVariable Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        List<CommentEntity> comments = commentRepository.findAllByPostId(post.getId());
        PostResponse postResponse = new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                comments.stream().map(comment -> new CommentResponse(
                        comment.getId(),
                        comment.getContent(),
                        comment.getCreatedAt(),
                        comment.getCreatedBy(),
                        comment.getUpdatedAt(),
                        comment.getDeletedAt(),
                        subCommentsRepository.findAllByCommentId(comment.getId()).stream().map(subComment -> new SubCommentResponse(
                                subComment.getId(),
                                subComment.getContent(),
                                subComment.getCreatedAt(),
                                subComment.getCreatedBy(),
                                subComment.getUpdatedAt(),
                                subComment.getDeletedAt()
                        )).toList()
                )).toList(),
                post.getCreatedAt(),
                post.getCreatedBy(),
                post.getUpdatedAt(),
                post.getDeletedAt()
        );
        return CommonResponse.ok(postResponse);
    }

    @GetMapping("/posts/comments/{id}")
    public CommonResponse<CommentResponse> getComment(@PathVariable Long id) {
        Optional<CommentEntity> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            CommentResponse commentResponse = new CommentResponse(
                    comment.get().getId(),
                    comment.get().getContent(),
                    comment.get().getCreatedAt(),
                    comment.get().getCreatedBy(),
                    comment.get().getUpdatedAt(),
                    comment.get().getDeletedAt(),
                    subCommentsRepository.findAllByCommentId(comment.get().getId())
                            .stream()
                            .map(subComment -> new SubCommentResponse(
                                    subComment.getId(),
                                    subComment.getContent(),
                                    subComment.getCreatedAt(),
                                    subComment.getCreatedBy(),
                                    subComment.getUpdatedAt(),
                                    subComment.getDeletedAt()
                            ))
                            .toList()
            );
            return CommonResponse.ok(commentResponse);
        } else {
            return CommonResponse.error();
        }
    }

    @PostMapping("/posts")
    public void createPost(@RequestHeader("x-device-key") String deviceKey, @RequestBody CreatePostRequest request) {
        PostEntity postEntity = new PostEntity(request.getTitle(), request.getContent(), deviceKey);
        postRepository.save(postEntity);
    }

    @PostMapping("/posts/{id}")
    public void createPostComment(@PathVariable Long id, @RequestHeader("x-device-key") String deviceKey, @RequestBody CreatePostCommentRequest request) {
        PostEntity post = postRepository.findById(id).orElseThrow(IllegalAccessError::new);
        CommentEntity commentEntity = new CommentEntity(request.getContent(), deviceKey, post);
        commentRepository.save(commentEntity);
    }

    @PostMapping("/posts/comments/{id}")
    public void createPostCommentSubComment(@PathVariable Long id, @RequestHeader("x-device-key") String deviceKey, @RequestBody CreatePostCommentRequest request) {
        CommentEntity comment = commentRepository.findById(id).orElseThrow(IllegalAccessError::new);
        SubCommentsEntity subCommentEntity = new SubCommentsEntity(request.getContent(), deviceKey, comment);
        subCommentsRepository.save(subCommentEntity);
    }


}
