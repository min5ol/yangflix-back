package com.min5ol.back.Repository;

import com.min5ol.back.Entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    // 엔티티의 'content' 객체의 id로 조회하도록 수정
    List<Episode> findByContent_Id(Long contentId);
    List<Episode> findByTitleContainingIgnoreCase(String keyword);
}
