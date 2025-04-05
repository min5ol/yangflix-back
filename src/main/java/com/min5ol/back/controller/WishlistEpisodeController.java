package com.min5ol.back.controller;

import com.min5ol.back.DTO.WishlistEpisodeResponse;
import com.min5ol.back.Security.CustomUserDetails;
import com.min5ol.back.Service.WishlistEpisodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users/wishlist/episodes")
public class WishlistEpisodeController {

    private final WishlistEpisodeService wishlistEpisodeService;

    public WishlistEpisodeController(WishlistEpisodeService wishlistEpisodeService) {
        this.wishlistEpisodeService = wishlistEpisodeService;
    }

    @PostMapping("/{episodeId}")
    public ResponseEntity<Map<String, String>> addWishlistEpisode(
            @PathVariable Long episodeId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        wishlistEpisodeService.addWishlistEpisode(userDetails.getUser().getId(), episodeId);
        return ResponseEntity.ok(Map.of("message", "에피소드를 찜 목록에 추가했습니다."));
    }

    @DeleteMapping("/{episodeId}")
    public ResponseEntity<Map<String, String>> removeWishlistEpisode(
            @PathVariable Long episodeId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        wishlistEpisodeService.removeWishlistEpisode(userDetails.getUser().getId(), episodeId);
        return ResponseEntity.ok(Map.of("message", "찜한 에피소드에서 제거되었습니다."));
    }

    @GetMapping
    public ResponseEntity<List<WishlistEpisodeResponse>> getWishlistEpisodes(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(
                wishlistEpisodeService.getWishlistEpisodesByUserId(userDetails.getUser().getId())
        );
    }
}
