package com.example.gymServer.mapper;

import com.example.gymServer.dto.MembershipPassDTO;
import com.example.gymServer.models.MembershipPass;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MembershipPassMapper {
    MembershipPassDTO toMembershipPassDTO(MembershipPass membershipPass);
    MembershipPass toMembershipPass(MembershipPassDTO membershipPassDTO);
    List<MembershipPassDTO> toMembershipPassDTOs(List<MembershipPass> membershipPasses);
}
