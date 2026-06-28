package ru.otus.wishlist.service.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import ru.otus.protobuf.CreateUserRequest;
import ru.otus.protobuf.Error;
import ru.otus.protobuf.UserResponse;
import ru.otus.wishlist.database.UserDatabaseService;
import ru.otus.wishlist.domain.User;
import ru.otus.wishlist.dto.CreateUserDto;
import ru.otus.wishlist.mappers.DtoMappers;
import ru.otus.wishlist.mappers.UserMappers;
import ru.otus.wishlist.service.UserService;
import ru.otus.wishlist.validation.ErrorFactory;
import ru.otus.wishlist.validation.ValidationService;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final ValidationService validationService;
  private final UserMappers userMappers;
  private final UserDatabaseService userDatabaseService;
  private final DtoMappers dtoMappers;
  private final ErrorFactory errorFactory;

  @Override
  public UserResponse createUser(CreateUserRequest requestBody) {
    CreateUserDto dto = dtoMappers.map(requestBody);
    List<Error> errorItems = new ArrayList<>();
    validationService.validate(dto, errorItems);
    if (!errorItems.isEmpty()) {
      return UserResponse.newBuilder().addAllErrors(errorItems).build();
    }
    try {
      User result = userDatabaseService.saveUser(userMappers.map(dto));
      return UserResponse.newBuilder().setItem(userMappers.map(result)).build();
    } catch (DuplicateKeyException e) {
      return UserResponse.newBuilder()
          .addErrors(errorFactory.createClientError("Пользователь с таким именем уже существует"))
          .build();
    } catch (Exception e) {
      log.error("Internal server error", e);
      return errorFactory.getInternalServerErrorUserResponse();
    }
  }

  @Override
  public List<UserResponse> findAll() {
    try {
      return userDatabaseService.findAll().stream()
          .map(user -> UserResponse.newBuilder().setItem(userMappers.map(user)).build())
          .toList();
    } catch (Exception e) {
      log.error("Internal server error", e);
      return List.of(errorFactory.getInternalServerErrorUserResponse());
    }
  }
}
