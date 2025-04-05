package com.min5ol.back.controller;

import com.min5ol.back.DTO.GuestResponse;
import com.min5ol.back.Service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping("/create")
    public ResponseEntity<GuestResponse> createGuestSession() {
        return ResponseEntity.ok(guestService.createGuestSession());
    }

    @GetMapping("/{sessionToken}")
    public ResponseEntity<GuestResponse> getGuestBySessionToken(@PathVariable String sessionToken) {
        return ResponseEntity.ok(guestService.getGuestBySessionToken(sessionToken));
    }
}
