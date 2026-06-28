package ru.otus.wishlist.validation;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.otus.protobuf.Error;
import ru.otus.protobuf.GiftResponse;
import ru.otus.protobuf.UserResponse;
import ru.otus.protobuf.WishlistResponse;

@Component
public class ErrorFactory {

  private final Error internalServerError =
      Error.newBuilder().setCode("500").setMessage("Неизвестная ошибка сервера").build();

  @Getter
  private final UserResponse internalServerErrorUserResponse =
      UserResponse.newBuilder().addErrors(internalServerError).build();

  @Getter
  private final WishlistResponse internalServerErrorWishlistResponse =
      WishlistResponse.newBuilder().addErrors(internalServerError).build();

  @Getter
  private final GiftResponse internalServerErrorGiftResponse =
      GiftResponse.newBuilder().addErrors(internalServerError).build();

  public Error createClientError(String message) {
    return Error.newBuilder().setCode("400").setMessage(message).build();
  }
}
