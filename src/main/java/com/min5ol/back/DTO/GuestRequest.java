package com.min5ol.back.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GuestRequest {
    // 게스트는 단순히 sessionToken(혹은 sessionId)만 전달하면 됨
    private String sessionToken;
}
