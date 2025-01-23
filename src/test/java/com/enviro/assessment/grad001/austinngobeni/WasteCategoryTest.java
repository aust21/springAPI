package com.enviro.assessment.grad001.austinngobeni;

import com.enviro.assessment.grad001.austinngobeni.controller.CategoryController;
import com.enviro.assessment.grad001.austinngobeni.model.Guidelines;
import com.enviro.assessment.grad001.austinngobeni.model.WasteCategories;
import com.enviro.assessment.grad001.austinngobeni.repo.GuideLineRepo;
import com.enviro.assessment.grad001.austinngobeni.repo.WasteCategoryRepo;
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
class WasteCategoryTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private CategoryController categoryController;

	@Mock
	private WasteCategoryRepo categoryRepo;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initialize @Mock and @InjectMocks
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build(); // Initialize MockMvc
	}

	@Test
	void testGetAllData() {
		WasteCategories cat1 = new WasteCategories(1L, "Category 1");
		WasteCategories cat2 = new WasteCategories(2L, "Category 2");

		when(categoryRepo.findAll()).thenReturn(Arrays.asList(cat1, cat2));

		ResponseEntity<List<WasteCategories>> response = categoryController.getAllData();
		assertEquals(2, response.getBody().size());
		assertEquals("Category 1", response.getBody().get(0).getDisposal());
		assertEquals("Category 2", response.getBody().get(1).getDisposal());
	}

	@Test
	void testGetDataByIdFound() {
		WasteCategories cat = new WasteCategories(1L, "Category 1");

		when(categoryRepo.findById(1L)).thenReturn(Optional.of(cat));

		ResponseEntity<WasteCategories> response = categoryController.getDataById(1L);
		assertEquals(1L, response.getBody().getId());
		assertEquals("Category 1", response.getBody().getDisposal());
	}

	@Test
	void testGetDataByIdNotFound() {
		when(categoryRepo.findById(1L)).thenReturn(Optional.empty());

		ResponseEntity<WasteCategories> response = categoryController.getDataById(1L);
		assertEquals(204, response.getStatusCodeValue());
	}

	@Test
	void testAdd() throws Exception {
		WasteCategories cat = new WasteCategories(1L, "Reduce use recycle");
		Mockito.when(categoryRepo.save(any(WasteCategories.class))).thenReturn(cat);

		mockMvc.perform(post("/cat/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"id\":2,\"disposal\":\"New Category\"}"))
				.andExpect(status().isOk());

	}

	@Test
	void testUpdateByIdFound() throws Exception {
		WasteCategories existingCat = new WasteCategories(1L, "Old Category");
		WasteCategories updatedCat = new WasteCategories(1L, "Updated Category");
		Mockito.when(categoryRepo.findById(1L)).thenReturn(Optional.of(existingCat));
		Mockito.when(categoryRepo.save(any(WasteCategories.class))).thenReturn(updatedCat);

		mockMvc.perform(put("/cat/update/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"id\":1,\"disposal\":\"Updated Category\"}"))
				.andExpect(status().isOk());
	}

}
