package com.sparta.springcode.table;

import com.sparta.springcode.domain.Timestamped;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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

    @NotBlank(message = "제목을 입력해주세요.")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    @Column(name = "thoughts")
    private String thoughts;

//    @Column(nullable = false)
//    private Long userId;

    public Memory(String title, String thoughts) {
        this.title = title;
        this.thoughts = thoughts;
    } // 백업용

    // 게시글 생성 시 이용
//    public Memory(MemoryRequestDto requestDto) {
////        this.userId = userId; , Long userId
//        this.title = requestDto.getTitle();
//        this.thoughts = requestDto.getThoughts();
//    }

}
