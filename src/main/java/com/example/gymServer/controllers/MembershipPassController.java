package com.example.gymServer.controllers;

import com.example.gymServer.dto.MembershipPassDTO;
import com.example.gymServer.services.MembershipPassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/membership-pass")
@RequiredArgsConstructor
public class MembershipPassController {

    private final MembershipPassService membershipPassService;

    @GetMapping
    public ResponseEntity<List<MembershipPassDTO>> getAllMembershipPasses() {
        return ResponseEntity.ok(membershipPassService.getAllMembershipPasses());
    }

    @PostMapping
    public ResponseEntity<MembershipPassDTO> createMembershipPass(@RequestBody MembershipPassDTO membershipPassDTO) {
        MembershipPassDTO savedMembershipPass = membershipPassService.saveMembershipPass(membershipPassDTO);
        return ResponseEntity.ok(savedMembershipPass);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembershipPass(@PathVariable Long id) {
        membershipPassService.deleteMembershipPass(id);
        return ResponseEntity.noContent().build();
    }
}
