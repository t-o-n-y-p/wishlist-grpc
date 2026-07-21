package ru.otus.wishlist.validation;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.otus.protobuf.Error;
import ru.otus.wishlist.database.GiftDatabaseService;
import ru.otus.wishlist.database.UserDatabaseService;
import ru.otus.wishlist.database.WishlistDatabaseService;
import ru.otus.wishlist.domain.Gift;
import ru.otus.wishlist.domain.User;
import ru.otus.wishlist.domain.Wishlist;

@Service
@AllArgsConstructor
public class ValidationService {

  private final Validator validator;
  private final UserDatabaseService userDatabaseService;
  private final WishlistDatabaseService wishlistDatabaseService;
  private final GiftDatabaseService giftDatabaseService;
  private final ErrorFactory errorFactory;

  public void validate(Object value, List<Error> errorItems) {
    Errors errors = new BeanPropertyBindingResult(value, "value");
    validator.validate(value, errors);
    if (errors.hasErrors()) {
      errorItems.addAll(
          errors.getFieldErrors().stream()
              .map(e -> errorFactory.createClientError(e.getDefaultMessage()))
              .toList());
    }
  }

  public Optional<User> validateUserByName(String name, List<Error> errors) {
    if (name == null) {
      return Optional.empty();
    }
    Optional<User> userByName = userDatabaseService.findByName(name);
    if (userByName.isEmpty()) {
      errors.add(errorFactory.createClientError("Пользователя с таким именем не существует"));
      return Optional.empty();
    }
    return userByName;
  }

  public Optional<Wishlist> validateWishlistById(Long id, List<Error> errors) {
    if (id == null) {
      return Optional.empty();
    }
    Optional<Wishlist> wishlistById = wishlistDatabaseService.findById(id);
    if (wishlistById.isEmpty()) {
      errors.add(errorFactory.createClientError("Списка желаний с таким id не существует"));
      return Optional.empty();
    }
    return wishlistById;
  }

  public Optional<Gift> validateGiftById(Long id, List<Error> errors) {
    if (id == null) {
      return Optional.empty();
    }
    Optional<Gift> giftById = giftDatabaseService.findById(id);
    if (giftById.isEmpty()) {
      errors.add(errorFactory.createClientError("Подарка с таким id не существует"));
      return Optional.empty();
    }
    return giftById;
  }
}
