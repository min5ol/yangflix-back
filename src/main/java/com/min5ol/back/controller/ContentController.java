package com.min5ol.back.controller;

import com.min5ol.back.DTO.ContentRequest;
import com.min5ol.back.DTO.ContentResponse;
import com.min5ol.back.Service.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping
    public ResponseEntity<ContentResponse> addContent(@RequestBody ContentRequest contentDto) {
        return ResponseEntity.ok(contentService.addContent(contentDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentResponse> updateContent(
            @PathVariable Long id, @RequestBody ContentRequest updatedDto) {
        return ResponseEntity.ok(contentService.updateContent(id, updatedDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ContentResponse>> getAllContent() {
        return ResponseEntity.ok(contentService.getAllContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponse> getContentById(@PathVariable Long id) {
        return ResponseEntity.ok(contentService.getContentById(id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<ContentResponse> getContentByTitle(@PathVariable String title) {
        return ResponseEntity.ok(contentService.getContentByTitle(title));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContentResponse>> searchContents(@RequestParam String keyword) {
        return ResponseEntity.ok(contentService.searchContents(keyword));
    }

    // ✅ 추가: 연도 + 타이틀 기반 조회 (releaseDate 기준)
    @GetMapping("/search-by-title-year")
    public ResponseEntity<ContentResponse> getContentByTitleAndYear(
            @RequestParam String title,
            @RequestParam String year) {
        return ResponseEntity.ok(contentService.getContentByTitleAndYear(title, year));
    }
}
