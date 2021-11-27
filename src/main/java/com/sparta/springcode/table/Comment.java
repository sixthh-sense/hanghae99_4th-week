package com.sparta.springcode.table;


import com.sparta.springcode.domain.Timestamped;
import com.sparta.springcode.dto.CommentDto;
import com.sparta.springcode.security.UserDetailsImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "내용을 입력해주세요.")
    @Column(name = "commentary", nullable = false)
    private String commentary;


    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "memoryId", nullable = false)
    private Long memoryId;

    public Comment(String commentary, String username, Long memoryId) {
        this.commentary = commentary;
        this.username = username;
        this.memoryId = memoryId;
    } // 백업용

    // 댓글 생성 시 이용
    public Comment(CommentDto cDto, @AuthenticationPrincipal UserDetailsImpl userDetails, Memory memory) {
        this.commentary = cDto.getCommentary();
        this.username = userDetails.getUsername();
        this.memoryId = memory.getId();
    }

    public Comment(CommentDto cDto) {
        this.commentary = cDto.getCommentary();
        this.username = cDto.getUsername();
        this.memoryId = cDto.getMemoryId();
    }

    public void update(CommentDto cDto) {
        this.commentary = cDto.getCommentary();
    } // 오른쪽이 왼쪽에 있는 걸 바꾸는 거지?
    // update method를 알고는 있었는데 다시 본 기분이다.
}
