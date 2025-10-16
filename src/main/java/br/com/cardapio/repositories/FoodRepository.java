package br.com.cardapio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardapio.models.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{
	 List<Food> findByTitleContainingIgnoreCase(String title);
}
