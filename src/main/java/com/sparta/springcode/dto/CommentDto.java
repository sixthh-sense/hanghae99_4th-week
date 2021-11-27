package com.sparta.springcode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto {
    private String username;
    private String commentary;
    public Long memoryId;
}
