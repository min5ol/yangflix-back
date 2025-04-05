package com.min5ol.back.Service;

import com.min5ol.back.DTO.GuestResponse;
import com.min5ol.back.Entity.Guest;
import com.min5ol.back.Repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    // 새로운 게스트 세션 생성
    public GuestResponse createGuestSession() {
        String sessionToken = UUID.randomUUID().toString();
        Guest guest = Guest.builder()
                .sessionToken(sessionToken)
                .build();
        return new GuestResponse(guestRepository.save(guest));
    }

    // 기존 세션 확인 (sessionToken 기준)
    public GuestResponse getGuestBySessionToken(String sessionToken) {
        Optional<Guest> guest = guestRepository.findBySessionToken(sessionToken);
        return guest.map(GuestResponse::new)
                .orElseThrow(() -> new RuntimeException("세션이 유효하지 않습니다."));
    }
}
