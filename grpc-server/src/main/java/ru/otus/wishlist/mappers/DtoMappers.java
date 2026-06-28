package ru.otus.wishlist.mappers;

import org.mapstruct.Mapper;
import ru.otus.protobuf.CreateGiftRequest;
import ru.otus.protobuf.CreateUserRequest;
import ru.otus.protobuf.CreateWishlistRequest;
import ru.otus.protobuf.DeleteGiftRequest;
import ru.otus.protobuf.DeleteWishlistRequest;
import ru.otus.protobuf.GetGiftsRequest;
import ru.otus.protobuf.GetWishlistsRequest;
import ru.otus.protobuf.UpdateGiftRequest;
import ru.otus.protobuf.UpdateWishlistRequest;
import ru.otus.wishlist.dto.CreateGiftDto;
import ru.otus.wishlist.dto.CreateUserDto;
import ru.otus.wishlist.dto.CreateWishlistDto;
import ru.otus.wishlist.dto.DeleteGiftDto;
import ru.otus.wishlist.dto.DeleteWishlistDto;
import ru.otus.wishlist.dto.GetGiftsDto;
import ru.otus.wishlist.dto.GetWishlistsDto;
import ru.otus.wishlist.dto.UpdateGiftDto;
import ru.otus.wishlist.dto.UpdateWishlistDto;

@Mapper(config = CommonConfig.class)
public interface DtoMappers {

  CreateUserDto map(CreateUserRequest source);

  CreateWishlistDto map(CreateWishlistRequest source);

  GetWishlistsDto map(GetWishlistsRequest source);

  UpdateWishlistDto map(UpdateWishlistRequest source);

  DeleteWishlistDto map(DeleteWishlistRequest source);

  CreateGiftDto map(CreateGiftRequest source);

  GetGiftsDto map(GetGiftsRequest source);

  UpdateGiftDto map(UpdateGiftRequest source);

  DeleteGiftDto map(DeleteGiftRequest source);

  default String map(String source) {
    return source == null || source.isBlank() ? null : source.trim();
  }
}
