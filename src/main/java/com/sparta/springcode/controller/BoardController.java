package com.sparta.springcode.controller;

import com.sparta.springcode.dto.CommentDto;
import com.sparta.springcode.repository.CommentRepository;
import com.sparta.springcode.security.UserDetailsImpl;
import com.sparta.springcode.service.CommentService;
import com.sparta.springcode.table.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/memories/")
public class BoardController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping("detail/{id}/comments")
    public Comment writeComment(@PathVariable Long id, @RequestBody String commentary, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // DTO로 받아오려면 DTO에 입력된 값을 "다" 받아야 한다.
        commentary = commentary.split("=", 2)[1];
        CommentDto cDto = new CommentDto(userDetails.getUsername(), commentary, id);
        Comment comment = new Comment(cDto);
        return commentRepository.save(comment);
    }

    @GetMapping("detail/{id}/comments")
    public List<Comment> showWrittenComment(@PathVariable Long id) {
        Comment comment = new Comment();
        return commentRepository.findAllByMemoryIdOrderByModifiedAtDesc(id);
    }

    @PutMapping("detail/{id}/comments/{cId}")
    public Long editComment(@PathVariable Long cId, @RequestBody CommentDto cDto) {
        Comment comment = new Comment();
        cId = comment.getId();

        commentService.update(cId, cDto);
        return cId;
    }

    @DeleteMapping("detail/{id}/comments/{cId}")
    public Long deleteComment(@PathVariable Long cId) {
        Comment comment = new Comment();
        cId = comment.getId();
        commentRepository.deleteById(cId);
        return cId;
    }
}
//}
//    @PostMapping("add")
//    public String addMemory(CommentDto cDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        //로그인 되어있는 회원 테이블의 ID
//        cDto.setUsername(userDetails.getUsername());
//        Comment comment = new Comment(cDto);
//        commentRepository.save(comment);
//        return "redirect:detail/{id}";
//    }

//    model.addAttribute("comments", commentRepository.findAllByOrderByModifiedAtDesc());
//        try {
//        model.addAttribute("username", userDetails.getUsername());
//        model.addAttribute("comments", commentRepository.findAllByOrderByModifiedAtDesc());
//    } catch(Exception ignored) {
//
//    }





