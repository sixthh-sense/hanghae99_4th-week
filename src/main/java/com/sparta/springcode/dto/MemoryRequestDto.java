package com.sparta.springcode.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoryRequestDto {
    private String title;
    private String thoughts;
    private Long userId;
}
