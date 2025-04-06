package com.min5ol.back.Repository;

import com.min5ol.back.Entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByTitleContaining(String keyword);
    List<Content> findByGenre(String genre);
    List<Content> findByTitleContainingIgnoreCase(String keyword);
}
