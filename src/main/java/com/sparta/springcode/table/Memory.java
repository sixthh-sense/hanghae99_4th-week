package com.sparta.springcode.table;

import com.sparta.springcode.domain.Timestamped;
//import com.sparta.springcode.dto.CommentDto;
import com.sparta.springcode.dto.MemoryRequestDto;
import com.sparta.springcode.security.UserDetailsImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Memory extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "thoughts", nullable = false)
    private String thoughts;

    @Column(nullable = false)
    private Long userId;

    public Memory(String title, String thoughts, Long userId) {
        this.userId = userId;
        this.title = title;
        this.thoughts = thoughts;
    } // 백업용

    // 게시글 생성 시 이용
    public Memory(MemoryRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        this.userId = userDetails.getUser().getId();
        this.title = requestDto.getTitle();
        this.thoughts = requestDto.getThoughts();
    }

    public Memory(MemoryRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.title = requestDto.getTitle();
        this.thoughts = requestDto.getThoughts();
    }

}
