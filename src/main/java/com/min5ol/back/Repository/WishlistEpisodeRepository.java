package com.min5ol.back.Repository;

import com.min5ol.back.Entity.WishlistEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistEpisodeRepository extends JpaRepository<WishlistEpisode, Long> {
    List<WishlistEpisode> findByUser_Id(Long userId);
    boolean existsByUser_IdAndEpisode_Id(Long userId, Long episodeId);
    void deleteByUser_IdAndEpisode_Id(Long userId, Long episodeId);
}
