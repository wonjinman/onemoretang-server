package com.deercorp.blackcompany.post;

import com.deercorp.blackcompany.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/posts")
    public CommonResponse<List<PostEntity>> getAllPosts() {
        return CommonResponse.ok(postRepository.findAll());
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
                comment.getDeletedAt()
            )).toList(),
            post.getCreatedAt(),
            post.getCreatedBy(),
            post.getUpdatedAt(),
            post.getDeletedAt()
        );
        return CommonResponse.ok(postResponse);
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
}
