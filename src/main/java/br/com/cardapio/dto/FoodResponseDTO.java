package br.com.cardapio.dto;

import br.com.cardapio.models.Food;

public record FoodResponseDTO(Long id, String title, String descricao, String image, Double price) {

	public FoodResponseDTO(Food food) {
		this(food.getId(), food.getTitle(), food.getDescricao(), food.getImage(), food.getPrice());
	}
}
