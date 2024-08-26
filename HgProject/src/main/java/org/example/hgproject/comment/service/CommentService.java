package org.example.hgproject.comment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.hgproject.comment.dto.CommentDto;

import org.example.hgproject.comment.entity.CommentEntity;
import org.example.hgproject.comment.repository.CommentRepository;
import org.example.hgproject.post.entity.BoardEntity;
import org.example.hgproject.post.repository.BoardRepository;
import org.example.hgproject.user.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public CommentEntity createComment(Long postId, CommentDto commentDto, UserEntity userEntity) {
        BoardEntity boardEntity = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentDto.getCommentContent());
        commentEntity.setAuthor(commentDto.getCommentAuthor()); // 로그인된 사용자 이름으로 설정
        commentEntity.setPost(boardEntity);

        return commentRepository.save(commentEntity);
    }
}
