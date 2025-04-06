package com.min5ol.back.controller;

import com.min5ol.back.DTO.WishlistContentResponse;
import com.min5ol.back.Security.CustomUserDetails;
import com.min5ol.back.Service.WishlistContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/wishlist/contents")
public class WishlistContentController {

    private final WishlistContentService wishlistService;

    public WishlistContentController(WishlistContentService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public ResponseEntity<List<WishlistContentResponse>> getWishlist(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(wishlistService.getWishlistByUserId(userId));
    }

    @PostMapping("/{contentId}")
    public ResponseEntity<String> addWishlistContent(@PathVariable Long contentId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        wishlistService.addWishlistContent(userDetails.getUser().getId(), contentId);
        return ResponseEntity.ok("Content added to wishlist.");
    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity<String> removeWishlistContent(@PathVariable Long contentId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        wishlistService.removeWishlistContent(userDetails.getUser().getId(), contentId);
        return ResponseEntity.ok("Content removed from wishlist.");
    }
}