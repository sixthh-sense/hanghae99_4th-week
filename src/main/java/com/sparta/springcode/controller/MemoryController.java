package com.sparta.springcode.controller;

import javax.validation.Valid;

import com.sparta.springcode.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @PostMapping("add")
    public String addMemory(@Valid Memory memory, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-memory";
        }

        memoryRepository.save(memory);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid memory Id:" + id));
        model.addAttribute("memory", memory);
        return "update-memory";
    }

    @PostMapping("update/{id}")
    public String updateMemory(@PathVariable("id") long id, @Valid Memory memory, BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            memory.setId(id);
            return "update-memory";
        }

        memoryRepository.save(memory);
        model.addAttribute("memories", memoryRepository.findAllByOrderByModifiedAtDesc());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteMemory(@PathVariable("id") long id, Model model) {
        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid memory Id:" + id));
        memoryRepository.delete(memory);
        model.addAttribute("memories", memoryRepository.findAllByOrderByModifiedAtDesc());
        return "index";
    }

    @GetMapping("detail/{id}")
    public String commentMemory(@PathVariable("id") long id, Model model) {

        return "detail";
    }
}





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

