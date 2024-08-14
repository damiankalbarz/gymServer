package com.example.gymServer.membershipPass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipPassService {

    @Autowired
    private MembershipPassRepository membershipPassRepository;

    public List<MembershipPass> getAllMembershipPasses() {
        return membershipPassRepository.findAll();
    }

    public MembershipPass saveMembershipPass(MembershipPass membershipPass) {
        return membershipPassRepository.save(membershipPass);
    }

    public void deleteMembershipPass(Long id) {
        membershipPassRepository.deleteById(id);
    }
}
