package com.nutrilizr.foodsearch;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.nutrilizr.foodsearch.controllers.FoodController;

@WebMvcTest(FoodController.class)
public class FoodControllerTests {
	@Autowired 
	private MockMvc mvc;
	
	/**
	 * Basic test to check if the food search endpoint is working correctly, connecting to the
	 * Fat Secrets server successfully and providing plausible results.
	 * 
	 * @throws Exception
	 */
	@Test
	void searchFoodItems() throws Exception {
		// Since only the controller is tested, no "/api" prefix is used for the requested URL:
		final ResultActions result = mvc.perform(get("/food/search")
			.queryParam("query", "pasta")
			.queryParam("page", "1") // 2nd page; should be OK as long as there is enough pasta.
			.contentType(MediaType.APPLICATION_JSON));
		
		result
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$.length()", is(50)));
	}
	
	/**
	 * Basic test to check if the food endpoint is working correctly, connecting to the
	 * Fat Secrets server successfully and providing details for the requested food item.
	 * 
	 * @throws Exception
	 */
	@Test
	void findFoodItemById() throws Exception {
		// Since only the controller is tested, no "/api" prefix is used for the requested URL:
		final ResultActions result = mvc.perform(get("/food")
			.queryParam("id", "4424")
			.contentType(MediaType.APPLICATION_JSON));
		
		result
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id", is(4424)));
	}
}
