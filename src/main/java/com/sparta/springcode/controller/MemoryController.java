package com.sparta.springcode.controller;

import com.sparta.springcode.dto.MemoryRequestDto;
import com.sparta.springcode.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sparta.springcode.table.Memory;
import com.sparta.springcode.repository.MemoryRepository;


@Controller
@RequestMapping("/memories/")
public class MemoryController {

    private final MemoryRepository memoryRepository;

    @Autowired
    public MemoryController(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    @GetMapping("signup")
    public String showSignUpForm(Memory memory) {
        return "add-memory";
    }


    @PostMapping("add")
    public String addMemory(MemoryRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //로그인 되어있는 회원 테이블의 ID
        requestDto.setUserId(userDetails.getUser().getId());
        Memory memory = new Memory(requestDto);
        memoryRepository.save(memory);
        return "redirect:list";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("memories", memoryRepository.findAllByOrderByModifiedAtDesc());
        try {
            model.addAttribute("memories", memoryRepository.findAllByOrderByModifiedAtDesc());
            model.addAttribute("username", userDetails.getUsername());
        } catch(Exception ignored) {
        }
        return "index";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails, MemoryRequestDto requestDto) {

        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid memory Id:" + id));
        model.addAttribute("memory", memory);
        return "update-memory";
    }

    @PostMapping ("update/{id}")
    public String updateMemory(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails, MemoryRequestDto requestDto) {

        Long userId = userDetails.getUser().getId();

//        Memory memory = memoryRepository.findById(id)
//                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));

        requestDto.setUserId(userId);
        Memory memory = new Memory(requestDto);
        memory.setId(id);
        memoryRepository.save(memory);

        model.addAttribute("memories", memoryRepository.findAllByOrderByModifiedAtDesc());
        model.addAttribute("memory", memory.getId());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteMemory(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();

        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid memory Id:" + id));
        memoryRepository.delete(memory);
        model.addAttribute("memories", memoryRepository.findAllByOrderByModifiedAtDesc());
        return "index";
    }

    @GetMapping("detail/{id}")
    public String commentMemory(@PathVariable Long id, Model model) {
        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid memory Id:" + id));
        model.addAttribute("memory", memory);
        model.addAttribute("id", id);
        model.addAttribute("title", memory.getTitle());
        model.addAttribute("thoughts", memory.getThoughts());
        model.addAttribute("modifiedAt", memory.getModifiedAt());

        return "detail";
    }
}



//    @GetMapping("detail/{id}")
//    public String commentMemory(@PathVariable Long id, Model model) {
//        Memory memory = memoryRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid memory Id:" + id));
//        model.addAttribute("memory", memory);
//        model.addAttribute("title", memory.getTitle());
//        model.addAttribute("thoughts", memory.getThoughts());
//        model.addAttribute("modifiedAt", memory.getModifiedAt());
//
//        return "detail";
//    }




//    @PostMapping("/user/signup")
//    public String writeMemory(MemoryRequestDto requestDto) {
//        memoryService.registerMemory(requestDto);
//        return "index";
//    }
// 신규 게시글 등록
//    @PostMapping("/memories")
//    public Memory createMemory(@RequestBody MemoryRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        Long userId = userDetails.getUser().getId();
//        return memoryService.createMemory(requestDto, userId);
//    }
//
//    @GetMapping("/memories")
//    public List<Memory> readMemory() {
//        return memoryRepository.findAllByOrderByModifiedAtDesc();
//    }

