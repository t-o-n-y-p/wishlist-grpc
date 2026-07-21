package ru.otus.wishlist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateWishlistDto(
    @NotBlank(message = "Имя пользователя не может быть пустым") String username,
    @NotBlank(message = "Название не может быть пустым")
        @Size(max = 50, message = "Слишком длинное название")
        String title,
    @Size(max = 500, message = "Слишком длинное описание") String description) {}
