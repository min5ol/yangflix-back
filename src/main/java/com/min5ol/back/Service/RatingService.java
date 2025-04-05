package com.min5ol.back.Service;

import com.min5ol.back.DTO.RatingRequest;
import com.min5ol.back.DTO.RatingResponse;
import com.min5ol.back.Entity.Content;
import com.min5ol.back.Entity.Rating;
import com.min5ol.back.Entity.User;
import com.min5ol.back.Repository.ContentRepository;
import com.min5ol.back.Repository.RatingRepository;
import com.min5ol.back.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    public RatingService(RatingRepository ratingRepository, UserRepository userRepository, ContentRepository contentRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
    }

    // ✅ userId를 외부에서 받고 DTO에는 userId 제거됨
    public RatingResponse addRating(Long userId, RatingRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않습니다."));
        Content content = contentRepository.findById(request.getContentId())
                .orElseThrow(() -> new RuntimeException("해당 컨텐츠가 존재하지 않습니다."));
        Rating rating = Rating.builder()
                .user(user)
                .content(content)
                .rating(Rating.RatingType.valueOf(request.getRating()))
                .build();
        return new RatingResponse(ratingRepository.save(rating));
    }

    public RatingResponse updateRating(Long id, RatingRequest updatedDto) {
        Optional<Rating> existingRating = ratingRepository.findById(id);
        if (existingRating.isPresent()) {
            Rating rating = existingRating.get();
            rating.setRating(Rating.RatingType.valueOf(updatedDto.getRating()));
            return new RatingResponse(ratingRepository.save(rating));
        } else {
            throw new RuntimeException("해당 평가가 존재하지 않습니다.");
        }
    }

    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    public List<RatingResponse> getRatingsByContentId(Long contentId) {
        return ratingRepository.findByContent_Id(contentId).stream()
                .map(RatingResponse::new)
                .collect(Collectors.toList());
    }

    public List<RatingResponse> getRatingsByUserId(Long userId) {
        return ratingRepository.findByUser_Id(userId).stream()
                .map(RatingResponse::new)
                .collect(Collectors.toList());
    }
}
