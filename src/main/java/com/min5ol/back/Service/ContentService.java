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

    public ContentResponse addContent(ContentRequest contentDto) {
        Content content = Content.builder()
                .title(contentDto.getTitle())
                .description(contentDto.getDescription())
                .genre(contentDto.getGenre())
                .thumbnail(contentDto.getThumbnailUrl())
                .build();
        return new ContentResponse(contentRepository.save(content));
    }

    public ContentResponse updateContent(Long id, ContentRequest updatedDto) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 컨텐츠가 존재하지 않습니다."));
        content.setTitle(updatedDto.getTitle());
        content.setDescription(updatedDto.getDescription());
        content.setGenre(updatedDto.getGenre());
        content.setThumbnail(updatedDto.getThumbnailUrl());
        return new ContentResponse(contentRepository.save(content));
    }

    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    public List<ContentResponse> getAllContent() {
        return contentRepository.findAll().stream()
                .map(ContentResponse::new)
                .collect(Collectors.toList());
    }

    public ContentResponse getContentById(Long id) {
        return contentRepository.findById(id)
                .map(ContentResponse::new)
                .orElseThrow(() -> new RuntimeException("해당 컨텐츠가 존재하지 않습니다."));
    }

    // ✅ 단일 title 기준 조회 (가장 첫 번째 결과 반환)
    public ContentResponse getContentByTitle(String title) {
        List<Content> contents = contentRepository.findByTitle(title);
        if (contents.isEmpty()) {
            throw new RuntimeException("해당 타이틀의 콘텐츠가 존재하지 않습니다.");
        }
        return new ContentResponse(contents.get(0)); // 첫 번째 콘텐츠 반환
    }

    // ✅ 키워드 기반 검색
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

    // ✅ 핵심: releaseDate 기반으로 연도 필터링
    public ContentResponse getContentByTitleAndYear(String title, String year) {
        List<Content> contents = contentRepository.findByTitle(title);

        if (contents.isEmpty()) {
            throw new RuntimeException("해당 타이틀의 콘텐츠가 존재하지 않습니다.");
        }

        for (Content content : contents) {
            List<Episode> episodes = episodeRepository.findByContent_Id(content.getId());

            boolean hasMatch = episodes.stream()
                    .anyMatch(ep -> String.valueOf(ep.getReleaseDate().getYear()).equals(year));

            if (hasMatch) {
                return new ContentResponse(content);
            }
        }

        throw new RuntimeException("해당 타이틀의 콘텐츠는 있지만 해당 연도의 에피소드는 없습니다.");
    }
}
