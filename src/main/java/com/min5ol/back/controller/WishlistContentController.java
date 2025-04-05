package com.min5ol.back.controller;

import com.min5ol.back.DTO.WishlistContentResponse;
import com.min5ol.back.Security.CustomUserDetails;
import com.min5ol.back.Service.WishlistContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> addWishlistContent(
            @PathVariable Long contentId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        wishlistService.addWishlistContent(userDetails.getUser().getId(), contentId);
        return ResponseEntity.ok(Map.of("message", "콘텐츠를 찜 목록에 추가했습니다."));
    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity<Map<String, String>> removeWishlistContent(
            @PathVariable Long contentId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        wishlistService.removeWishlistContent(userDetails.getUser().getId(), contentId);
        return ResponseEntity.ok(Map.of("message", "찜한 콘텐츠에서 제거되었습니다."));
    }
}
