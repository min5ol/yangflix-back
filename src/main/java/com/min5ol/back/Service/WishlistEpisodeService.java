package com.min5ol.back.Service;

import com.min5ol.back.DTO.WishlistEpisodeResponse;
import com.min5ol.back.Entity.Episode;
import com.min5ol.back.Entity.User;
import com.min5ol.back.Entity.WishlistEpisode;
import com.min5ol.back.Repository.EpisodeRepository;
import com.min5ol.back.Repository.UserRepository;
import com.min5ol.back.Repository.WishlistEpisodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistEpisodeService {

    private final WishlistEpisodeRepository wishlistEpisodeRepository;
    private final EpisodeRepository episodeRepository;
    private final UserRepository userRepository;

    public WishlistEpisodeService(WishlistEpisodeRepository wishlistEpisodeRepository,
                                EpisodeRepository episodeRepository,
                                UserRepository userRepository) {
        this.wishlistEpisodeRepository = wishlistEpisodeRepository;
        this.episodeRepository = episodeRepository;
        this.userRepository = userRepository;
    }

    // 찜한 에피소드 추가
    public void addWishlistEpisode(Long userId, Long episodeId) {
        if (!wishlistEpisodeRepository.existsByUser_IdAndEpisode_Id(userId, episodeId)) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Episode episode = episodeRepository.findById(episodeId)
                    .orElseThrow(() -> new RuntimeException("Episode not found"));
            WishlistEpisode wishlistEpisode = WishlistEpisode.builder()
                    .user(user)
                    .episode(episode)
                    .build();
            wishlistEpisodeRepository.save(wishlistEpisode);
        }
    }

    // 찜한 에피소드 삭제
    public void removeWishlistEpisode(Long userId, Long episodeId) {
        wishlistEpisodeRepository.deleteByUser_IdAndEpisode_Id(userId, episodeId);
    }

    // 사용자의 찜한 에피소드 목록 조회 (제목 및 썸네일 포함)
    public List<WishlistEpisodeResponse> getWishlistEpisodesByUserId(Long userId) {
        return wishlistEpisodeRepository.findByUser_Id(userId).stream()
                .map(wishlistEpisode -> {
                    Episode episode = episodeRepository.findById(wishlistEpisode.getEpisode().getId())
                            .orElseThrow(() -> new RuntimeException("Episode not found"));
                    return new WishlistEpisodeResponse(wishlistEpisode, episode);
                }).collect(Collectors.toList());
    }
}
