package com.min5ol.back.Service;

import com.min5ol.back.DTO.ContentRequest;
import com.min5ol.back.DTO.ContentResponse;
import com.min5ol.back.Entity.Content;
import com.min5ol.back.Entity.Episode;
import com.min5ol.back.Repository.ContentRepository;
import com.min5ol.back.Repository.EpisodeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final EpisodeRepository episodeRepository;

    public ContentService(ContentRepository contentRepository, EpisodeRepository episodeRepository) {
        this.contentRepository = contentRepository;
        this.episodeRepository = episodeRepository;
    }

    // 컨텐츠 추가 (관리자용)
    public ContentResponse addContent(ContentRequest contentDto) {
        Content content = Content.builder()
                .title(contentDto.getTitle())
                .description(contentDto.getDescription())
                .genre(contentDto.getGenre())
                .thumbnail(contentDto.getThumbnailUrl())
                .build();
        Content savedContent = contentRepository.save(content);
        return new ContentResponse(savedContent);
    }

    // 컨텐츠 수정 (관리자용)
    public ContentResponse updateContent(Long id, ContentRequest updatedDto) {
        Optional<Content> existingContent = contentRepository.findById(id);
        if (existingContent.isPresent()) {
            Content content = existingContent.get();
            content.setTitle(updatedDto.getTitle());
            content.setDescription(updatedDto.getDescription());
            content.setGenre(updatedDto.getGenre());
            content.setThumbnail(updatedDto.getThumbnailUrl());
            return new ContentResponse(contentRepository.save(content));
        } else {
            throw new RuntimeException("해당 컨텐츠가 존재하지 않습니다.");
        }
    }

    // 컨텐츠 삭제 (관리자용)
    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    // 전체 컨텐츠 조회
    public List<ContentResponse> getAllContent() {
        return contentRepository.findAll().stream()
                .map(ContentResponse::new)
                .collect(Collectors.toList());
    }

    // 특정 컨텐츠 상세 조회
    public ContentResponse getContentById(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 컨텐츠가 존재하지 않습니다."));
        return new ContentResponse(content);
    }

    public List<ContentResponse> searchContents(String keyword) {
        List<Content> contentMatches = contentRepository.findByTitleContainingIgnoreCase(keyword);
        List<Episode> episodeMatches = episodeRepository.findByTitleContainingIgnoreCase(keyword);

        Set<Content> allMatches = new HashSet<>(contentMatches);
        for (Episode episode : episodeMatches) {
            allMatches.add(episode.getContent());
        }

        return allMatches.stream()
                .map(ContentResponse::new)
                .collect(Collectors.toList());
    }

}
