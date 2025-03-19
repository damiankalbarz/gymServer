package com.example.gymServer.controllers;

import com.example.gymServer.services.MembershipPassService;
import com.example.gymServer.models.MembershipPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/membership-pass")
public class MembershipPassController {

    @Autowired
    private MembershipPassService membershipPassService;

    @GetMapping
    public List<MembershipPass> getAllMembershipPasses() {
        return membershipPassService.getAllMembershipPasses();
    }

    @PostMapping
    public MembershipPass createMembershipPass(@RequestBody MembershipPass membershipPass) {
        return membershipPassService.saveMembershipPass(membershipPass);
    }

    @DeleteMapping("/{id}")
    public void deleteMembershipPass(@PathVariable Long id) {
        membershipPassService.deleteMembershipPass(id);
    }
}
