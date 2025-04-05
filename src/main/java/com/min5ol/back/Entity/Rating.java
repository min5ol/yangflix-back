package com.min5ol.back.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // 평가한 사용자를 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    // 평가 대상 컨텐츠를 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private Content content;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RatingType rating;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    public enum RatingType {
        LOW, MEDIUM, HIGH
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
