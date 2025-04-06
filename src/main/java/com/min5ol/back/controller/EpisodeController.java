package com.min5ol.back.controller;

import com.min5ol.back.DTO.EpisodeRequest;
import com.min5ol.back.DTO.EpisodeResponse;
import com.min5ol.back.Service.EpisodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/episodes")
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @PostMapping
    public ResponseEntity<EpisodeResponse> addEpisode(@RequestBody EpisodeRequest episodeRequest) {
        return ResponseEntity.ok(episodeService.addEpisode(episodeRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EpisodeResponse> updateEpisode(@PathVariable Long id, @RequestBody EpisodeRequest updatedDto) {
        return ResponseEntity.ok(episodeService.updateEpisode(id, updatedDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpisode(@PathVariable Long id) {
        episodeService.deleteEpisode(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/content/{contentId}")
    public ResponseEntity<List<EpisodeResponse>> getEpisodesByContentId(@PathVariable Long contentId) {
        return ResponseEntity.ok(episodeService.getEpisodesByContentId(contentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpisodeResponse> getEpisodeById(@PathVariable Long id) {
        return ResponseEntity.ok(episodeService.getEpisodeById(id));
    }
}
