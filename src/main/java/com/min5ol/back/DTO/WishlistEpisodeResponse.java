package com.min5ol.back.DTO;

import com.min5ol.back.Entity.WishlistEpisode;
import com.min5ol.back.Entity.Episode;
import lombok.Getter;

@Getter
public class WishlistEpisodeResponse {
    private Long id;
    private Long userId;
    private Long episodeId;
    private String episodeTitle;
    private String thumbnailUrl;

    public WishlistEpisodeResponse(WishlistEpisode wishlistEpisode, Episode episode) {
        this.id = wishlistEpisode.getId();
        this.userId = wishlistEpisode.getUser().getId();
        this.episodeId = wishlistEpisode.getEpisode().getId();
        this.episodeTitle = episode.getTitle();
        this.thumbnailUrl = episode.getThumbnail();
    }
}
