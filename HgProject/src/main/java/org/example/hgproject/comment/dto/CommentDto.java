package org.example.hgproject.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Long commentId;

    private String commentContent;

    private String commentAuthor; // 로그인된 사용자의 이름을 저장할 필드

    private Long postId;
}
