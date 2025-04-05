package com.min5ol.back.Repository;

import com.min5ol.back.Entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    // 엔티티의 필드명이 sessionToken임에 주의
    Optional<Guest> findBySessionToken(String sessionToken);
}
