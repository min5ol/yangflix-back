package com.min5ol.back.Repository;

import com.min5ol.back.Entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> findByContent_Id(Long contentId);
    List<Episode> findByContent_TitleAndReleaseDateStartingWith(String title, String year);
    List<Episode> findByTitleContainingIgnoreCase(String keyword);
}