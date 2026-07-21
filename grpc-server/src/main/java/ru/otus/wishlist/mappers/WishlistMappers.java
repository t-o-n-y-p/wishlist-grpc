package ru.otus.wishlist.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.protobuf.WishlistResponseItem;
import ru.otus.wishlist.domain.User;
import ru.otus.wishlist.domain.Wishlist;
import ru.otus.wishlist.dto.CreateWishlistDto;
import ru.otus.wishlist.dto.UpdateWishlistDto;

@Mapper(config = CommonConfig.class)
public interface WishlistMappers extends AggregateReferenceMappers<User, Long> {

  Wishlist map(CreateWishlistDto source, Long userId);

  @Mapping(target = "id", source = "source.id")
  @Mapping(target = "title", source = "source.title", defaultExpression = "java(wishlist.title())")
  @Mapping(
      target = "description",
      source = "source.description",
      defaultExpression = "java(wishlist.description())")
  Wishlist map(UpdateWishlistDto source, Wishlist wishlist);

  WishlistResponseItem map(Wishlist source);
}
