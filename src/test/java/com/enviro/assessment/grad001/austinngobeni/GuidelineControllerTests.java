package com.enviro.assessment.grad001.austinngobeni;

import com.enviro.assessment.grad001.austinngobeni.controller.GuidelineController;
import com.enviro.assessment.grad001.austinngobeni.model.Guidelines;
import com.enviro.assessment.grad001.austinngobeni.model.RecyclingTips;
import com.enviro.assessment.grad001.austinngobeni.repo.GuideLineRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GuidelineControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private GuidelineController guideController;

    @Mock
    private GuideLineRepo guideLineRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize @Mock and @InjectMocks
        mockMvc = MockMvcBuilders.standaloneSetup(guideController).build(); // Initialize MockMvc
    }

    @Test
    void testGetAllData() {
        Guidelines guide1 = new Guidelines(1L, "Guide 1");
        Guidelines guide2 = new Guidelines(2L, "Guide 2");

        when(guideLineRepo.findAll()).thenReturn(Arrays.asList(guide1, guide2));

        ResponseEntity<List<Guidelines>> response = guideController.getAllData();
        assertEquals(2, response.getBody().size());
        assertEquals("Guide 1", response.getBody().get(0).getGuideline());
        assertEquals("Guide 2", response.getBody().get(1).getGuideline());
    }

    @Test
    void testGetDataByIdFound() {
        Guidelines guide = new Guidelines(1L, "Guide 1");

        when(guideLineRepo.findById(1L)).thenReturn(Optional.of(guide));

        ResponseEntity<Guidelines> response = guideController.getDataById(1L);
        assertEquals(1L, response.getBody().getId());
        assertEquals("Guide 1", response.getBody().getGuideline());
    }

    @Test
    void testGetDataByIdNotFound() {
        when(guideLineRepo.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Guidelines> response = guideController.getDataById(1L);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testAdd() throws Exception {
        Guidelines guide = new Guidelines(1L, "Reduce use recycle");
        Mockito.when(guideLineRepo.save(any(Guidelines.class))).thenReturn(guide);

        mockMvc.perform(post("/guide/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":2,\"guideline\":\"New Guide\"}"))
                .andExpect(status().isOk());

    }

    @Test
    void testUpdateByIdFound() throws Exception {
        Guidelines existingGuide = new Guidelines(1L, "Old Guide");
        Guidelines updatedGuide = new Guidelines(1L, "Updated Guide");
        Mockito.when(guideLineRepo.findById(1L)).thenReturn(Optional.of(existingGuide));
        Mockito.when(guideLineRepo.save(any(Guidelines.class))).thenReturn(updatedGuide);

        mockMvc.perform(put("/guide/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"guideline\":\"Updated Guide\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteByIdFound() throws Exception {
        Guidelines guide = new Guidelines(1L, "Guide to delete");
        Mockito.when(guideLineRepo.findById(1L)).thenReturn(Optional.of(guide));
        Mockito.doNothing().when(guideLineRepo).deleteById(1L);

        mockMvc.perform(delete("/guide/delete/1"))
                .andExpect(status().isOk());
    }

}
