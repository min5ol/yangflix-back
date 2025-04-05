package com.min5ol.back.DTO;

import com.min5ol.back.Entity.Content;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ContentResponse {
    private Long id;
    private String title;
    private String description;
    private String genre;
    private String thumbnailUrl;
    // 추가로 생성/수정 날짜를 포함할 수도 있음
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Entity -> DTO 변환
    public ContentResponse(Content content) {
        this.id = content.getId();
        this.title = content.getTitle();
        this.description = content.getDescription();
        this.genre = content.getGenre();
        this.thumbnailUrl = content.getThumbnail();
        // getter 메서드 이름은 엔티티 필드와 맞춤 (엔티티에서 thumbnail 필드 사용)
        // createdAt, updatedAt도 필요하면 아래와 같이 추가할 수 있음:
        // this.createdAt = content.getCreatedAt();
        // this.updatedAt = content.getUpdatedAt();
    }
}
