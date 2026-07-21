package ru.otus.wishlist.dto;

import jakarta.validation.constraints.NotBlank;

public record GetWishlistsDto(
    @NotBlank(message = "Имя пользователя не может быть пустым") String username) {}
