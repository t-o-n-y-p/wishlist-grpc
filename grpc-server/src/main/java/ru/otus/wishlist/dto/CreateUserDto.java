package ru.otus.wishlist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
    @NotBlank(message = "Имя пользователя не может быть пустым")
        @Size(max = 20, message = "Слишком длинное имя пользователя")
        @Pattern(
            regexp = "[a-z]*",
            message = "В имени пользователя допускаются только строчные латинские буквы")
        String username) {}
