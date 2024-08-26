package org.example.hgproject.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.hgproject.comment.dto.CommentDto;
import org.example.hgproject.comment.entity.CommentEntity;
import org.example.hgproject.comment.service.CommentService;
import org.example.hgproject.user.entity.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public CommentDto createComment(@PathVariable Long postId,
                                    @RequestBody CommentDto commentDto,
                                    @AuthenticationPrincipal UserEntity userEntity) {
        CommentEntity commentEntity = commentService.createComment(postId, commentDto, userEntity);

        CommentDto responseDto = new CommentDto();
        responseDto.setCommentId(commentEntity.getCommentId());
        responseDto.setCommentContent(commentEntity.getContent());
        responseDto.setCommentAuthor(commentEntity.getAuthor());
        responseDto.setPostId(commentEntity.getPost().getPostId());

        return responseDto;
    }
}
