package ru.otus.wishlist.controller;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;
import ru.otus.protobuf.CreateUserRequest;
import ru.otus.protobuf.GetUsersRequest;
import ru.otus.protobuf.UserResponse;
import ru.otus.protobuf.UsersServiceGrpc;
import ru.otus.wishlist.service.UserService;

@GrpcService
@AllArgsConstructor
public class UsersGrpcController extends UsersServiceGrpc.UsersServiceImplBase {

  private final UserService userService;

  @Override
  public void createUser(CreateUserRequest request, StreamObserver<UserResponse> responseObserver) {
    responseObserver.onNext(userService.createUser(request));
    responseObserver.onCompleted();
  }

  @Override
  public void getUsers(GetUsersRequest request, StreamObserver<UserResponse> responseObserver) {
    for (UserResponse userResponse : userService.findAll()) {
      responseObserver.onNext(userResponse);
    }
    responseObserver.onCompleted();
  }
}
