package com.min5ol.back.Service;

import com.min5ol.back.DTO.WishlistContentResponse;
import com.min5ol.back.Entity.Content;
import com.min5ol.back.Entity.User;
import com.min5ol.back.Entity.WishlistContent;
import com.min5ol.back.Repository.ContentRepository;
import com.min5ol.back.Repository.UserRepository;
import com.min5ol.back.Repository.WishlistContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistContentService {

    private final WishlistContentRepository wishlistContentRepository;
    private final ContentRepository contentRepository;
    private final UserRepository userRepository;

    public WishlistContentService(WishlistContentRepository wishlistContentRepository,
            ContentRepository contentRepository,
            UserRepository userRepository) {
        this.wishlistContentRepository = wishlistContentRepository;
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
    }

    // ✅ 사용자의 찜한 컨텐츠 목록 조회
    public List<WishlistContentResponse> getWishlistByUserId(Long userId) {
        return wishlistContentRepository.findByUser_Id(userId).stream()
                .map(wishlist -> {
                    Content content = contentRepository.findById(wishlist.getContent().getId())
                            .orElseThrow(() -> new RuntimeException("Content not found"));
                    return new WishlistContentResponse(wishlist, content);
                }).collect(Collectors.toList());
    }

    // ✅ 찜 추가
    public void addWishlistContent(Long userId, Long contentId) {
        if (!wishlistContentRepository.existsByUser_IdAndContent_Id(userId, contentId)) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Content content = contentRepository.findById(contentId)
                    .orElseThrow(() -> new RuntimeException("Content not found"));
            WishlistContent wishlist = WishlistContent.builder()
                    .user(user)
                    .content(content)
                    .build();
            wishlistContentRepository.save(wishlist);
        }
    }

    // ✅ 찜 제거
    public void removeWishlistContent(Long userId, Long contentId) {
        wishlistContentRepository.deleteByUser_IdAndContent_Id(userId, contentId);
    }
}
