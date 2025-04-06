package com.min5ol.back.controller;

import com.min5ol.back.DTO.WishlistEpisodeResponse;
import com.min5ol.back.Security.CustomUserDetails;
import com.min5ol.back.Service.WishlistEpisodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/wishlist/episodes")
public class WishlistEpisodeController {

    private final WishlistEpisodeService wishlistEpisodeService;

    public WishlistEpisodeController(WishlistEpisodeService wishlistEpisodeService) {
        this.wishlistEpisodeService = wishlistEpisodeService;
    }

    @PostMapping("/{episodeId}")
    public ResponseEntity<String> addWishlistEpisode(@PathVariable Long episodeId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        wishlistEpisodeService.addWishlistEpisode(userDetails.getUser().getId(), episodeId);
        return ResponseEntity.ok("Episode added to wishlist.");
    }

    @DeleteMapping("/{episodeId}")
    public ResponseEntity<String> removeWishlistEpisode(@PathVariable Long episodeId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        wishlistEpisodeService.removeWishlistEpisode(userDetails.getUser().getId(), episodeId);
        return ResponseEntity.ok("Episode removed from wishlist.");
    }

    @GetMapping
    public ResponseEntity<List<WishlistEpisodeResponse>> getWishlistEpisodes(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(wishlistEpisodeService.getWishlistEpisodesByUserId(userDetails.getUser().getId()));
    }
}