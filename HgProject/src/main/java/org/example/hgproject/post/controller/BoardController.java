package org.example.hgproject.post.controller;

import lombok.RequiredArgsConstructor;
import org.example.hgproject.post.dto.BoardDto;
import org.example.hgproject.post.entity.BoardEntity;
import org.example.hgproject.post.service.BoardService;
import org.example.hgproject.user.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 리스트 조회
    @GetMapping
    public ResponseEntity<List<BoardEntity>> getAllBoards() {
        List<BoardEntity> boardList = boardService.getAllBoards();
        return ResponseEntity.ok(boardList);
    }

    // 게시글 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<BoardEntity> getBoard(@PathVariable Long postId) {
        BoardEntity boardEntity = boardService.getBoard(postId);
        return ResponseEntity.ok(boardEntity);
    }

    // 게시글 생성
    @PostMapping
    public ResponseEntity<BoardEntity> createBoard(
            @ModelAttribute BoardDto boardDto,
            @AuthenticationPrincipal UserEntity userEntity) throws IOException {
        BoardEntity boardEntity = boardService.createBoard(boardDto);
        return new ResponseEntity<>(boardEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<BoardEntity> updateBoard(
            @PathVariable Long postId,
            @RequestBody BoardDto boardDto,
            @AuthenticationPrincipal UserEntity userEntity) throws AccessDeniedException {
        BoardEntity updatedBoard = boardService.updateBoard(postId, boardDto, userEntity);
        return ResponseEntity.ok(updatedBoard);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserEntity userEntity) throws AccessDeniedException {
        boardService.deleteBoard(postId, userEntity);
        return ResponseEntity.noContent().build();
    }
}
