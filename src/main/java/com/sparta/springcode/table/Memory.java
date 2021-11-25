package com.sparta.springcode.table;

import com.sparta.springcode.domain.Timestamped;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


@Entity
public class Memory extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Thoughts are mandatory")
    @Column(name = "thoughts")
    private String thoughts;

    @Column
    private String comment;

    public Memory() {}

    public Memory(String title, String thoughts, String comment) {
        this.title = title;
        this.thoughts = thoughts;
        this.comment = comment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public String getThoughts() {
        return thoughts;
    }

    public String getComment() {
        return comment;
    }

}
