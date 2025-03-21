package com.example.gymServer.membershipPass;

import com.example.gymServer.controllers.MembershipPassController;
import com.example.gymServer.dto.MembershipPassDTO;
import com.example.gymServer.models.MembershipPass;
import com.example.gymServer.services.MembershipPassService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MembershipPassControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MembershipPassService membershipPassService;

    @InjectMocks
    private MembershipPassController membershipPassController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(membershipPassController).build();
    }

    @Test
    public void testGetAllMembershipPasses() throws Exception {
        MembershipPassDTO membershipPass = new MembershipPassDTO();
        given(membershipPassService.getAllMembershipPasses()).willReturn(Collections.singletonList(membershipPass));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/membership-pass"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists());
    }

    @Test
    public void testCreateMembershipPass() throws Exception {
        MembershipPassDTO membershipPass = new MembershipPassDTO();
        given(membershipPassService.saveMembershipPass(membershipPass)).willReturn(membershipPass);

        mockMvc.perform(post("/api/v1/membership-pass")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(membershipPass)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMembershipPass() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/v1/membership-pass/{id}", id))
                .andExpect(status().isOk());

        then(membershipPassService).should().deleteMembershipPass(id);
    }
}