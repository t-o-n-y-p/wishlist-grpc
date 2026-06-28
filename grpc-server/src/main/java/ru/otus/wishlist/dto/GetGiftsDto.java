package ru.otus.wishlist.dto;

import jakarta.validation.constraints.NotNull;

public record GetGiftsDto(
    @NotNull(message = "ID списка желаний не может быть пустым") Long wishlistId) {}
