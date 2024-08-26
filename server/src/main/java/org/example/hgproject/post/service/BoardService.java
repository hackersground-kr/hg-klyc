package org.example.hgproject.post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.hgproject.comment.dto.CommentDto;
import org.example.hgproject.post.dto.BoardDto;
import org.example.hgproject.post.entity.BoardEntity;
import org.example.hgproject.post.repository.BoardRepository;
import org.example.hgproject.user.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final String uploadDir = "uploads/";

    public BoardEntity createBoard(BoardDto boardDto, UserEntity userEntity) throws IOException {
        // 게시글 작성
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setAuthor(boardDto.getAuthor());
        boardEntity.setContent(boardDto.getContent());
        boardEntity.setTitle(boardDto.getTitle());

        if (boardDto.getImageFile() != null && !boardDto.getImageFile().isEmpty()) {
            String imageUrl = saveImage(boardDto.getImageFile());
            boardEntity.setImageUrl(imageUrl);
        }

        return boardRepository.save(boardEntity);
    }

    private String saveImage(MultipartFile imageFile) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(imageFile.getOriginalFilename());
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new IOException("Could not save image file: " + fileName, e);
        }
    }

    public BoardEntity getBoard(Long postId) {
        return boardRepository.findBoardEntityByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
    }

    public List<BoardEntity> getAllBoards() {
        return boardRepository.findAll();
    }

    @Transactional
    public BoardEntity updateBoard(Long postId, BoardDto boardDto, UserEntity userEntity) throws AccessDeniedException {
        BoardEntity boardEntity = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        boardEntity.setTitle(boardDto.getTitle());
        boardEntity.setContent(boardDto.getContent());

        return boardRepository.save(boardEntity);
    }

    @Transactional
    public void deleteBoard(Long postId, UserEntity userEntity) throws AccessDeniedException {
        BoardEntity boardEntity = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        boardRepository.delete(boardEntity);
    }

    public BoardDto getBoardWithComments(Long postId) {
        BoardEntity boardEntity = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        BoardDto boardDto = new BoardDto();
        boardDto.setPostId(boardEntity.getPostId());
        boardDto.setTitle(boardEntity.getTitle());
        boardDto.setContent(boardEntity.getContent());
        boardDto.setAuthor(boardEntity.getAuthor());
        boardDto.setImageUrl(boardEntity.getImageUrl());

        List<CommentDto> commentDto = boardEntity.getComments().stream()
                .map(comment -> {
                    CommentDto dto = new CommentDto();
                    dto.setCommentId(comment.getCommentId());
                    dto.setCommentContent(comment.getContent());
                    dto.setCommentAuthor(comment.getAuthor());
                    dto.setPostId(comment.getPost().getPostId());
                    return dto;
                })
                .collect(Collectors.toList());

        boardDto.setComments(commentDto);
        return boardDto;
    }
}
