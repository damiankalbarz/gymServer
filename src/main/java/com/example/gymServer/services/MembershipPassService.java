package com.example.gymServer.services;

import com.example.gymServer.dto.MembershipPassDTO;
import com.example.gymServer.mapper.MembershipPassMapper;
import com.example.gymServer.models.MembershipPass;
import com.example.gymServer.repository.MembershipPassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipPassService {

    private final MembershipPassRepository membershipPassRepository;
    private final MembershipPassMapper membershipPassMapper;

    public List<MembershipPassDTO> getAllMembershipPasses() {
        List<MembershipPass> passes = membershipPassRepository.findAll();
        return membershipPassMapper.toMembershipPassDTOs(passes);
    }

    public MembershipPassDTO saveMembershipPass(MembershipPassDTO membershipPassDTO) {
        MembershipPass pass = membershipPassMapper.toMembershipPass(membershipPassDTO);
        pass = membershipPassRepository.save(pass);
        return membershipPassMapper.toMembershipPassDTO(pass);
    }

    public void deleteMembershipPass(Long id) {
        membershipPassRepository.deleteById(id);
    }
}
