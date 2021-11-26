package com.sparta.springcode.table;

import com.sparta.springcode.domain.Timestamped;
import lombok.Getter;
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

    public Memory() {}

    public Memory(String title, String thoughts) {
        this.title = title;
        this.thoughts = thoughts;
    }
}
