package ru.otus.wishlist.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.otus.protobuf.CreateWishlistRequest;
import ru.otus.protobuf.DeleteWishlistRequest;
import ru.otus.protobuf.Error;
import ru.otus.protobuf.GetWishlistsRequest;
import ru.otus.protobuf.UpdateWishlistRequest;
import ru.otus.protobuf.WishlistResponse;
import ru.otus.wishlist.database.WishlistDatabaseService;
import ru.otus.wishlist.domain.User;
import ru.otus.wishlist.domain.Wishlist;
import ru.otus.wishlist.dto.CreateWishlistDto;
import ru.otus.wishlist.dto.DeleteWishlistDto;
import ru.otus.wishlist.dto.GetWishlistsDto;
import ru.otus.wishlist.dto.UpdateWishlistDto;
import ru.otus.wishlist.mappers.DtoMappers;
import ru.otus.wishlist.mappers.WishlistMappers;
import ru.otus.wishlist.service.WishlistService;
import ru.otus.wishlist.validation.ErrorFactory;
import ru.otus.wishlist.validation.ValidationService;

@Service
@AllArgsConstructor
@Slf4j
public class WishlistServiceImpl implements WishlistService {

  private final ValidationService validationService;
  private final WishlistMappers wishlistMappers;
  private final WishlistDatabaseService wishlistDatabaseService;
  private final DtoMappers dtoMappers;
  private final ErrorFactory errorFactory;

  @Override
  public WishlistResponse createWishlist(CreateWishlistRequest requestBody) {
    CreateWishlistDto dto = dtoMappers.map(requestBody);
    List<Error> errorItems = new ArrayList<>();
    validationService.validate(dto, errorItems);
    Optional<User> user = validationService.validateUserByName(dto.username(), errorItems);
    if (!errorItems.isEmpty() || user.isEmpty()) {
      return WishlistResponse.newBuilder().addAllErrors(errorItems).build();
    }
    Wishlist wishlist = wishlistMappers.map(dto, user.get().id());
    try {
      Wishlist result = wishlistDatabaseService.saveWishlist(wishlist);
      return WishlistResponse.newBuilder().setItem(wishlistMappers.map(result)).build();
    } catch (Exception e) {
      log.error("Internal server error", e);
      return errorFactory.getInternalServerErrorWishlistResponse();
    }
  }

  @Override
  public List<WishlistResponse> getWishlists(GetWishlistsRequest requestBody) {
    GetWishlistsDto dto = dtoMappers.map(requestBody);
    List<Error> errorItems = new ArrayList<>();
    validationService.validate(dto, errorItems);
    Optional<User> user = validationService.validateUserByName(dto.username(), errorItems);
    if (!errorItems.isEmpty() || user.isEmpty()) {
      return List.of(WishlistResponse.newBuilder().addAllErrors(errorItems).build());
    }
    try {
      return wishlistDatabaseService.findByUserId(user.get().id()).stream()
          .map(
              wishlist ->
                  WishlistResponse.newBuilder().setItem(wishlistMappers.map(wishlist)).build())
          .toList();
    } catch (Exception e) {
      log.error("Internal server error", e);
      return List.of(errorFactory.getInternalServerErrorWishlistResponse());
    }
  }

  @Override
  public WishlistResponse updateWishlist(UpdateWishlistRequest requestBody) {
    UpdateWishlistDto dto = dtoMappers.map(requestBody);
    List<Error> errorItems = new ArrayList<>();
    validationService.validate(dto, errorItems);
    Optional<Wishlist> wishlist = validationService.validateWishlistById(dto.id(), errorItems);
    if (!errorItems.isEmpty() || wishlist.isEmpty()) {
      return WishlistResponse.newBuilder().addAllErrors(errorItems).build();
    }
    try {
      Wishlist updatedWishlist =
          wishlistDatabaseService.saveWishlist(wishlistMappers.map(dto, wishlist.get()));
      return WishlistResponse.newBuilder().setItem(wishlistMappers.map(updatedWishlist)).build();
    } catch (Exception e) {
      log.error("Internal server error", e);
      return errorFactory.getInternalServerErrorWishlistResponse();
    }
  }

  @Override
  public WishlistResponse deleteWishlist(DeleteWishlistRequest requestBody) {
    DeleteWishlistDto dto = dtoMappers.map(requestBody);
    List<Error> errorItems = new ArrayList<>();
    validationService.validate(dto, errorItems);
    if (!errorItems.isEmpty()) {
      return WishlistResponse.newBuilder().addAllErrors(errorItems).build();
    }
    try {
      wishlistDatabaseService.deleteWishlist(dto.id());
      return WishlistResponse.newBuilder().build();
    } catch (DataIntegrityViolationException e) {
      return WishlistResponse.newBuilder()
          .addErrors(
              errorFactory.createClientError(
                  "Нельзя удалить список желаний, в котором есть подарки"))
          .build();
    } catch (Exception e) {
      log.error("Internal server error", e);
      return errorFactory.getInternalServerErrorWishlistResponse();
    }
  }
}
