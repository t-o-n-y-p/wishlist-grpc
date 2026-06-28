package ru.otus.wishlist.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.protobuf.UserResponseItem;
import ru.otus.wishlist.domain.User;
import ru.otus.wishlist.dto.CreateUserDto;

@Mapper(config = CommonConfig.class)
public interface UserMappers {

  @Mapping(target = "name", source = "username")
  User map(CreateUserDto source);

  @Mapping(target = "username", source = "name")
  UserResponseItem map(User source);
}
