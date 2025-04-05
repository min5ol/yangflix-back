package com.min5ol.back.Repository;

import com.min5ol.back.Entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByContent_Id(Long contentId);
    List<Rating> findByUser_Id(Long userId);
}
