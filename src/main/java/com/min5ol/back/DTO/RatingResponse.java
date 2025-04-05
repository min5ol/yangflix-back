package com.min5ol.back.DTO;

import com.min5ol.back.Entity.Rating;
import lombok.Getter;

@Getter
public class RatingResponse {
    private Long id;
    private Long userId;
    private Long contentId;
    private String rating;

    public RatingResponse(Rating rating) {
        this.id = rating.getId();
        this.userId = rating.getUser().getId();
        this.contentId = rating.getContent().getId();
        this.rating = rating.getRating().name();
    }
}
