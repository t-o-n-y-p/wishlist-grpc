package ru.otus.wishlist.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.protobuf.GiftResponseItem;
import ru.otus.wishlist.domain.Gift;
import ru.otus.wishlist.domain.Wishlist;
import ru.otus.wishlist.dto.CreateGiftDto;
import ru.otus.wishlist.dto.UpdateGiftDto;

@Mapper(config = CommonConfig.class)
public interface GiftMappers extends AggregateReferenceMappers<Wishlist, Long> {

  Gift map(CreateGiftDto source);

  @Mapping(target = "id", source = "source.id")
  @Mapping(target = "title", source = "source.title", defaultExpression = "java(gift.title())")
  @Mapping(target = "price", source = "source.price", defaultExpression = "java(gift.price())")
  @Mapping(
      target = "description",
      source = "source.description",
      defaultExpression = "java(gift.description())")
  @Mapping(
      target = "reserved",
      source = "source.reserved",
      defaultExpression = "java(gift.reserved())")
  Gift map(UpdateGiftDto source, Gift gift);

  GiftResponseItem map(Gift source);
}
