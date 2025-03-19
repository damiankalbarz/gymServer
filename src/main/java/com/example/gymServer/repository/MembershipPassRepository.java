package com.example.gymServer.repository;


import com.example.gymServer.models.MembershipPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipPassRepository extends JpaRepository<MembershipPass, Long> {
}
