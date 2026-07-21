package ru.otus.wishlist.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.protobuf.CreateGiftRequest;
import ru.otus.protobuf.DeleteGiftRequest;
import ru.otus.protobuf.Error;
import ru.otus.protobuf.GetGiftsRequest;
import ru.otus.protobuf.GiftResponse;
import ru.otus.protobuf.UpdateGiftRequest;
import ru.otus.wishlist.database.GiftDatabaseService;
import ru.otus.wishlist.domain.Gift;
import ru.otus.wishlist.domain.Wishlist;
import ru.otus.wishlist.dto.CreateGiftDto;
import ru.otus.wishlist.dto.DeleteGiftDto;
import ru.otus.wishlist.dto.GetGiftsDto;
import ru.otus.wishlist.dto.UpdateGiftDto;
import ru.otus.wishlist.mappers.DtoMappers;
import ru.otus.wishlist.mappers.GiftMappers;
import ru.otus.wishlist.service.GiftService;
import ru.otus.wishlist.validation.ErrorFactory;
import ru.otus.wishlist.validation.ValidationService;

@Service
@AllArgsConstructor
@Slf4j
public class GiftServiceImpl implements GiftService {

  private final ValidationService validationService;
  private final GiftMappers giftMappers;
  private final GiftDatabaseService giftDatabaseService;
  private final DtoMappers dtoMappers;
  private final ErrorFactory errorFactory;

  @Override
  public GiftResponse createGift(CreateGiftRequest requestBody) {
    CreateGiftDto dto = dtoMappers.map(requestBody);
    List<Error> errorItems = new ArrayList<>();
    validationService.validate(dto, errorItems);
    Optional<Wishlist> wishlist =
        validationService.validateWishlistById(dto.wishlistId(), errorItems);
    if (!errorItems.isEmpty() || wishlist.isEmpty()) {
      return GiftResponse.newBuilder().addAllErrors(errorItems).build();
    }
    Gift gift = giftMappers.map(dto);
    try {
      Gift result = giftDatabaseService.saveGift(gift);
      return GiftResponse.newBuilder().setItem(giftMappers.map(result)).build();
    } catch (Exception e) {
      log.error("Internal server error", e);
      return errorFactory.getInternalServerErrorGiftResponse();
    }
  }

  @Override
  public List<GiftResponse> getGifts(GetGiftsRequest requestBody) {
    GetGiftsDto dto = dtoMappers.map(requestBody);
    List<Error> errorItems = new ArrayList<>();
    validationService.validate(dto, errorItems);
    Optional<Wishlist> wishlist =
        validationService.validateWishlistById(dto.wishlistId(), errorItems);
    if (!errorItems.isEmpty() || wishlist.isEmpty()) {
      return List.of(GiftResponse.newBuilder().addAllErrors(errorItems).build());
    }
    try {
      return giftDatabaseService.findByWishlistId(wishlist.get().id()).stream()
          .map(gift -> GiftResponse.newBuilder().setItem(giftMappers.map(gift)).build())
          .toList();
    } catch (Exception e) {
      log.error("Internal server error", e);
      return List.of(errorFactory.getInternalServerErrorGiftResponse());
    }
  }

  @Override
  public GiftResponse updateGift(UpdateGiftRequest requestBody) {
    UpdateGiftDto dto = dtoMappers.map(requestBody);
    List<Error> errorItems = new ArrayList<>();
    validationService.validate(dto, errorItems);
    Optional<Gift> gift = validationService.validateGiftById(dto.id(), errorItems);
    if (!errorItems.isEmpty() || gift.isEmpty()) {
      return GiftResponse.newBuilder().addAllErrors(errorItems).build();
    }
    try {
      Gift updatedGift = giftDatabaseService.saveGift(giftMappers.map(dto, gift.get()));
      return GiftResponse.newBuilder().setItem(giftMappers.map(updatedGift)).build();
    } catch (Exception e) {
      log.error("Internal server error", e);
      return errorFactory.getInternalServerErrorGiftResponse();
    }
  }

  @Override
  public GiftResponse deleteGift(DeleteGiftRequest requestBody) {
    DeleteGiftDto dto = dtoMappers.map(requestBody);
    List<Error> errorItems = new ArrayList<>();
    validationService.validate(dto, errorItems);
    if (!errorItems.isEmpty()) {
      return GiftResponse.newBuilder().addAllErrors(errorItems).build();
    }
    try {
      giftDatabaseService.deleteGift(dto.id());
      return GiftResponse.newBuilder().build();
    } catch (Exception e) {
      log.error("Internal server error", e);
      return errorFactory.getInternalServerErrorGiftResponse();
    }
  }
}
