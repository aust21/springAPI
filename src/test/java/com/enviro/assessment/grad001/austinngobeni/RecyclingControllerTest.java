package com.enviro.assessment.grad001.austinngobeni;

import com.enviro.assessment.grad001.austinngobeni.controller.RecyclingController;
import com.enviro.assessment.grad001.austinngobeni.model.RecyclingTips;
import com.enviro.assessment.grad001.austinngobeni.repo.RecylingTipsRepo;
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
class RecyclingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @InjectMocks
    private RecyclingController recyclingController;

    @Mock
    private RecylingTipsRepo recyclingRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize @Mock and @InjectMocks
        mockMvc = MockMvcBuilders.standaloneSetup(recyclingController).build(); // Initialize MockMvc
    }

    @Test
    void testGetAllData() {
        RecyclingTips tip1 = new RecyclingTips(1L, "Tip 1");
        RecyclingTips tip2 = new RecyclingTips(2L, "Tip 2");

        when(recyclingRepo.findAll()).thenReturn(Arrays.asList(tip1, tip2));

        ResponseEntity<List<RecyclingTips>> response = recyclingController.getAllData();
        assertEquals(2, response.getBody().size());
        assertEquals("Tip 1", response.getBody().get(0).getRecyclingTip());
        assertEquals("Tip 2", response.getBody().get(1).getRecyclingTip());
    }

    @Test
    void testGetDataByIdFound() {
        RecyclingTips tip = new RecyclingTips(1L, "Tip 1");

        when(recyclingRepo.findById(1L)).thenReturn(Optional.of(tip));

        ResponseEntity<RecyclingTips> response = recyclingController.getDataById(1L);
        assertEquals(1L, response.getBody().getId());
        assertEquals("Tip 1", response.getBody().getRecyclingTip());
    }

    @Test
    void testGetDataByIdNotFound() {
        when(recyclingRepo.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<RecyclingTips> response = recyclingController.getDataById(1L);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testAdd() throws Exception {
        RecyclingTips tip = new RecyclingTips(1L, "Reduce use recycle");
        Mockito.when(recyclingRepo.save(any(RecyclingTips.class))).thenReturn(tip);

        mockMvc.perform(post("/rec/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"recyclingTip\":\"New Tip\"}"))
                .andExpect(status().isOk());

    }

    @Test
    void testUpdateByIdFound() throws Exception {
        RecyclingTips existingTip = new RecyclingTips(1L, "Old Tip");
        RecyclingTips updatedTip = new RecyclingTips(1L, "Updated Tip");
        Mockito.when(recyclingRepo.findById(1L)).thenReturn(Optional.of(existingTip));
        Mockito.when(recyclingRepo.save(any(RecyclingTips.class))).thenReturn(updatedTip);

        mockMvc.perform(put("/rec/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"recyclingTip\":\"Updated Tip\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteByIdFound() throws Exception {
        RecyclingTips tip = new RecyclingTips(1L, "Tip to delete");
        Mockito.when(recyclingRepo.findById(1L)).thenReturn(Optional.of(tip));
        Mockito.doNothing().when(recyclingRepo).deleteById(1L);

        mockMvc.perform(delete("/rec/delete/1"))
                .andExpect(status().isOk());
    }

}
