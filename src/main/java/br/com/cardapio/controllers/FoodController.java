package br.com.cardapio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardapio.dto.FoodRequestDTO;
import br.com.cardapio.dto.FoodResponseDTO;
import br.com.cardapio.models.Food;
import br.com.cardapio.repositories.FoodRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/food")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FoodController {

	@Autowired
	private FoodRepository foodRepository;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping
	public List<FoodResponseDTO> getAll() {
		return foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping
	public ResponseEntity<FoodResponseDTO> salveFood(@RequestBody @Valid FoodRequestDTO data) {
	    Food foodData = new Food(data);
	    Food saved = foodRepository.save(foodData);
	    return ResponseEntity.ok(new FoodResponseDTO(saved));
	}

	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/{id}")
	public void deleteFod(@PathVariable Long id) { 
		foodRepository.deleteById(id);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/{id}")
	public ResponseEntity<FoodResponseDTO> getFoodById(@PathVariable Long id) {
	    return foodRepository.findById(id)
	            .map(food -> ResponseEntity.ok(new FoodResponseDTO(food)))
	            .orElse(ResponseEntity.notFound().build());
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/{id}")
	public ResponseEntity<FoodResponseDTO> updateFood(
	        @PathVariable Long id,
	        @RequestBody @Valid FoodRequestDTO data) {

	    return foodRepository.findById(id)
	            .map(food -> {
	                food.setTitle(data.title());
	                food.setImage(data.image());
	                food.setPrice(data.price());
	                food.setDescricao(data.descricao());
	                Food updated = foodRepository.save(food);
	                return ResponseEntity.ok(new FoodResponseDTO(updated));
	            })
	            .orElse(ResponseEntity.notFound().build());
	}

	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/search/{title}")
	public ResponseEntity<List<Food>> seachFood(@PathVariable String title) {
	    List<Food> results = foodRepository.findByTitleContainingIgnoreCase(title);
	    return ResponseEntity.ok(results);
	}



}
