package ru.otus.wishlist.service;

import java.util.List;
import ru.otus.protobuf.CreateUserRequest;
import ru.otus.protobuf.UserResponse;

public interface UserService {

  UserResponse createUser(CreateUserRequest requestBody);

  List<UserResponse> findAll();
}
