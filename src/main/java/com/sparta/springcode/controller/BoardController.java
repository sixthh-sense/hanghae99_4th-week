package com.sparta.springcode.controller;

import com.sparta.springcode.dto.CommentDto;
import com.sparta.springcode.dto.MemoryRequestDto;
import com.sparta.springcode.repository.CommentRepository;
import com.sparta.springcode.repository.MemoryRepository;
import com.sparta.springcode.table.Comment;
import com.sparta.springcode.table.Memory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/memories/")
public class BoardController {

    private final MemoryRepository memoryRepository;
    private final CommentRepository commentRepository;

    @GetMapping("detail/{id}")
    public String showCommentForm(@PathVariable Long id, Model model) {

        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid memory Id:" + id));
        model.addAttribute("memory", memory);
        model.addAttribute("title", memory.getTitle());
        model.addAttribute("thoughts", memory.getThoughts());
        model.addAttribute("modifiedAt", memory.getModifiedAt());
        return "detailed";
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





