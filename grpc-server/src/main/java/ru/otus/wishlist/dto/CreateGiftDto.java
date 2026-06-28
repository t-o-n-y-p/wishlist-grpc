package ru.otus.wishlist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateGiftDto(
    @NotNull(message = "ID списка желаний не может быть пустым") Long wishlistId,
    @NotBlank(message = "Название не может быть пустым")
        @Size(max = 50, message = "Слишком длинное название")
        String title,
    @NotNull(message = "Цена не может быть пустой") @Positive(message = "Цена должна быть больше 0")
        Long price,
    @Size(max = 500, message = "Слишком длинное описание") String description) {}
