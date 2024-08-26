package org.example.hgproject.comment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hgproject.post.entity.BoardEntity;

@Entity
@Getter
@Setter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    private String author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private BoardEntity post;
}
