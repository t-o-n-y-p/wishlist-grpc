package ru.otus.wishlist.dto;

import jakarta.validation.constraints.NotNull;

public record DeleteWishlistDto(@NotNull(message = "ID не может быть пустым") Long id) {}
