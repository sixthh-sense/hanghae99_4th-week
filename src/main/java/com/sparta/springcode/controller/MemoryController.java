package com.sparta.springcode.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String showSignupForm(Memory memory) {
        return "add-memory";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("memory", memoryRepository.findAllByOrderByModifiedAtDesc());
        return "index";
    }

    @PostMapping("add")
    public String addMemory(@Valid Memory memory, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-memory";
        }
        memoryRepository.save(memory);
        model.addAttribute("memory", memoryRepository.findAllByOrderByModifiedAtDesc());
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID " + id));
        model.addAttribute("memory", memory);
        return "update-memory";
    }

    @PostMapping("update/{id}")
    public String updateMemory(@PathVariable Long id, @Valid Memory memory, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            memory.setId(id);
            return "update-memory";
        }

        memoryRepository.save(memory);
        model.addAttribute("memory", memoryRepository.findAllByOrderByModifiedAtDesc());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteMemory(@PathVariable Long id, Model model) {
        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid memory Id: " + id));
        memoryRepository.delete(memory);
        model.addAttribute("memory", memoryRepository.findAllByOrderByModifiedAtDesc());
        return "index";
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

}
