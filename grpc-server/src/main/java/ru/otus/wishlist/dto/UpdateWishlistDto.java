package ru.otus.wishlist.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateWishlistDto(
    @NotNull(message = "ID не может быть пустым") Long id,
    @Size(max = 50, message = "Слишком длинное название") String title,
    @Size(max = 500, message = "Слишком длинное описание") String description) {}
