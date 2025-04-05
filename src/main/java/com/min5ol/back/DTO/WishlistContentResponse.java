package com.min5ol.back.DTO;

import com.min5ol.back.Entity.WishlistContent;
import com.min5ol.back.Entity.Content;
import lombok.Getter;

@Getter
public class WishlistContentResponse {
    private Long id;
    private Long userId;
    private Long contentId;
    private String contentTitle;
    private String thumbnailUrl;

    public WishlistContentResponse(WishlistContent wishlistContent, Content content) {
        this.id = wishlistContent.getId();
        this.userId = wishlistContent.getUser().getId();
        this.contentId = wishlistContent.getContent().getId();
        this.contentTitle = content.getTitle();
        this.thumbnailUrl = content.getThumbnail();
    }
}
