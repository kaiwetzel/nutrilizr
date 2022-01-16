package com.nutrilizr.foodsearch.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.services.Response;

@PropertySource({"classpath:secret-${spring.profiles.active}.properties"})
@RequestMapping("food")
@RestController
public class FoodController {
	
	/**
	 * fatsecret.com API consumer key.
	 */
	@Value("${fatsecret.key}")
	private String key;
	
	/**
	 * fatsecret.com API consumer secret.
	 */
	@Value("${fatsecret.secret}")
	private String secret;
	
	/**
	 * Search for Fat Secret food items
	 * 
	 * @param query The string to search foods for.
	 * @param page Optional page number in the paged result list. 
	 */
	@GetMapping("/search")
	public List<CompactFood> search(
			@RequestParam String query, 
			@RequestParam(required = false, defaultValue = "0") int page) {
		
		FatsecretService service = new FatsecretService(key, secret);
		
		// Retrieve the list of food items on the requested page for the query string:		
		Response<CompactFood> response = service.searchFoods(query, page);
		
		// Return list of compact food items:
		List<CompactFood> results = response.getResults();
		
		return results;
	}
}
