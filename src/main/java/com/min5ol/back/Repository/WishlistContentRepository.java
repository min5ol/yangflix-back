package com.min5ol.back.Repository;

import com.min5ol.back.Entity.WishlistContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistContentRepository extends JpaRepository<WishlistContent, Long> {
    List<WishlistContent> findByUser_Id(Long userId);
    boolean existsByUser_IdAndContent_Id(Long userId, Long contentId);
    void deleteByUser_IdAndContent_Id(Long userId, Long contentId);
}
