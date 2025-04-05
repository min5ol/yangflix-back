package com.min5ol.back.DTO;

import com.min5ol.back.Entity.Guest;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class GuestResponse {
    private Long id;
    private String sessionToken;
    private LocalDateTime createdAt;

    public GuestResponse(Guest guest) {
        this.id = guest.getId();
        this.sessionToken = guest.getSessionToken();
        this.createdAt = guest.getCreatedAt();
    }
}
