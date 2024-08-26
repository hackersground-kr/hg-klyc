package org.example.hgproject.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hgproject.comment.entity.CommentEntity;

import java.util.List;

@Entity
@Getter
@Setter
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;

    private String content;

    private String author;

    private String imageUrl;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments;
}
