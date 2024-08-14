package com.example.gymServer.membershipPass;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipPassRepository extends JpaRepository<MembershipPass, Long> {
}
