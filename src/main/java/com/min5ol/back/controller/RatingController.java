package com.min5ol.back.controller;

import com.min5ol.back.DTO.RatingRequest;
import com.min5ol.back.DTO.RatingResponse;
import com.min5ol.back.Security.CustomUserDetails;
import com.min5ol.back.Service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<RatingResponse> addRating(
            @RequestBody RatingRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(ratingService.addRating(userDetails.getUser().getId(), request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingResponse> updateRating(
            @PathVariable Long id,
            @RequestBody RatingRequest updatedDto) {
        return ResponseEntity.ok(ratingService.updateRating(id, updatedDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/content/{contentId}")
    public ResponseEntity<List<RatingResponse>> getRatingsByContentId(@PathVariable Long contentId) {
        return ResponseEntity.ok(ratingService.getRatingsByContentId(contentId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<RatingResponse>> getRatingsByUser(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(ratingService.getRatingsByUserId(userDetails.getUser().getId()));
    }
}
