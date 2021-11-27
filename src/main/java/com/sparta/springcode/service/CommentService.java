package com.sparta.springcode.service;

import com.sparta.springcode.dto.CommentDto;
import com.sparta.springcode.repository.CommentRepository;
import com.sparta.springcode.table.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Long update(Long cId, CommentDto cDto) { // 얘도 dto가 아니라 따로따로 지정을 해줘야 하나?
        Comment comment = new Comment();
        cId = comment.getId();
        comment = commentRepository.findById(cId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 없습니다.")
        );
        comment.update(cDto);
        return cId;
    }
}
