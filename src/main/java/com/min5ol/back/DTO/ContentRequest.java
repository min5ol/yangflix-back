package com.min5ol.back.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContentRequest {
    private String title;
    private String description;
    private String genre;
    private String thumbnailUrl;
}
