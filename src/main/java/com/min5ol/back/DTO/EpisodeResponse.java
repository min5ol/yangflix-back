package com.min5ol.back.DTO;

import com.min5ol.back.Entity.Episode;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class EpisodeResponse {
    private Long id;
    private Long contentId;
    private String title;
    private int episodeNumber;
    private LocalDate releaseDate;
    private String thumbnailUrl;
    private String videoUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EpisodeResponse(Episode episode) {
        this.id = episode.getId();
        this.contentId = episode.getContent().getId();
        this.title = episode.getTitle();
        this.episodeNumber = episode.getEpisodeNumber();
        this.releaseDate = episode.getReleaseDate();
        this.thumbnailUrl = episode.getThumbnail();
        this.videoUrl = episode.getVideoUrl();
        this.createdAt = episode.getCreatedAt();
        this.updatedAt = episode.getUpdatedAt();
    }
}
