package com.min5ol.back.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EpisodeRequest {
    private String title;
    private int episodeNumber;
    private LocalDate releaseDate;
    private String thumbnailUrl;
    private String videoUrl;
    private Long contentId;
}
