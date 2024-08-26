package org.example.hgproject.post.repository;

import org.example.hgproject.post.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    Optional<BoardEntity> findBoardEntityByPostId(Long postId);
}
