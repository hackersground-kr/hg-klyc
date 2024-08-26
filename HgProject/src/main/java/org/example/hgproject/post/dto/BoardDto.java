package org.example.hgproject.post.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hgproject.comment.dto.CommentDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class BoardDto {
    private Long postId;

    private String title;

    private String content;

    private String author;

    private MultipartFile imageFile;

    private String imageUrl;

    private List<CommentDto> comments;
}
