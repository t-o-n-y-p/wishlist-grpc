package ru.otus.wishlist.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateGiftDto(
    @NotNull(message = "ID не может быть пустым") Long id,
    @Size(max = 50, message = "Слишком длинное название") String title,
    @Positive(message = "Цена должна быть больше 0") Long price,
    @Size(max = 500, message = "Слишком длинное описание") String description,
    Boolean reserved) {}
