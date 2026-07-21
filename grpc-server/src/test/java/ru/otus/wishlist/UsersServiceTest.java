package ru.otus.wishlist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;
import org.assertj.core.presentation.Representation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.protobuf.CreateUserRequest;
import ru.otus.protobuf.GetUsersRequest;
import ru.otus.protobuf.UserResponse;
import ru.otus.protobuf.UserResponseItem;
import ru.otus.protobuf.UsersServiceGrpc;
import ru.otus.wishlist.config.GrpcIntegrationTest;

@GrpcIntegrationTest
public class UsersServiceTest {

  @Autowired private UsersServiceGrpc.UsersServiceBlockingStub stub;
  @Autowired private Representation representation;

  @Test
  void createUserTest() {
    CreateUserRequest request = CreateUserRequest.newBuilder().setUsername("test").build();
    UserResponse actual = stub.createUser(request);
    UserResponse expected =
        UserResponse.newBuilder()
            .setItem(UserResponseItem.newBuilder().setId(1).setUsername("test").build())
            .build();
    assertThat(actual).withRepresentation(representation).isEqualTo(expected);
  }

  @Test
  void getUsersTest() {
    GetUsersRequest request = GetUsersRequest.newBuilder().build();
    Iterator<UserResponse> actual = stub.getUsers(request);
    List<UserResponse> expected =
        List.of(
            UserResponse.newBuilder()
                .setItem(UserResponseItem.newBuilder().setId(-2).setUsername("test2").build())
                .build(),
            UserResponse.newBuilder()
                .setItem(UserResponseItem.newBuilder().setId(-1).setUsername("test1").build())
                .build());
    assertThat(actual).toIterable().withRepresentation(representation).isEqualTo(expected);
  }
}
