package br.com.cardapio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FoodRequestDTO(
		@Size(max = 50, message = "No título são no máximo 50 caracteres!")
        @NotBlank(message = "O título não pode estar em branco!")
        String title,

        @Size(max = 100, message = "Na descrição são no máximo 100 caracteres!")
        @NotBlank(message = "A descrição não pode estar em branco!")
        String descricao,

        @NotBlank(message = "Prescisa de uma imagem!")
        String image,

        @NotNull(message = "O preço é obrigatório!")
        Double price
) {}

