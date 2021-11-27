package com.sparta.springcode.repository;

import com.sparta.springcode.table.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByModifiedAtDesc();
    List<Comment> findAllByMemoryIdOrderByModifiedAtDesc(Long memoryId);
    List<Comment> findAllByIdOrderByModifiedAtDesc(Long memoryId);
}
