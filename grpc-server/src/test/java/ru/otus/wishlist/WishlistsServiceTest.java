package ru.otus.wishlist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;
import org.assertj.core.presentation.Representation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.protobuf.CreateWishlistRequest;
import ru.otus.protobuf.DeleteWishlistRequest;
import ru.otus.protobuf.GetWishlistsRequest;
import ru.otus.protobuf.UpdateWishlistRequest;
import ru.otus.protobuf.WishlistResponse;
import ru.otus.protobuf.WishlistResponseItem;
import ru.otus.protobuf.WishlistsServiceGrpc;
import ru.otus.wishlist.config.GrpcIntegrationTest;

@GrpcIntegrationTest
public class WishlistsServiceTest {

  @Autowired private WishlistsServiceGrpc.WishlistsServiceBlockingStub stub;
  @Autowired private Representation representation;

  @Test
  void createWishlistTest() {
    CreateWishlistRequest request =
        CreateWishlistRequest.newBuilder().setUsername("test1").setTitle("wishlist").build();
    WishlistResponse actual = stub.createWishlist(request);
    WishlistResponse expected =
        WishlistResponse.newBuilder()
            .setItem(WishlistResponseItem.newBuilder().setId(1).setTitle("wishlist").build())
            .build();
    assertThat(actual).withRepresentation(representation).isEqualTo(expected);
  }

  @Test
  void updateWishlistTest() {
    UpdateWishlistRequest request =
        UpdateWishlistRequest.newBuilder().setId(-1).setTitle("updated").build();
    WishlistResponse actual = stub.updateWishlist(request);
    WishlistResponse expected =
        WishlistResponse.newBuilder()
            .setItem(WishlistResponseItem.newBuilder().setId(-1).setTitle("updated").build())
            .build();
    assertThat(actual).withRepresentation(representation).isEqualTo(expected);
  }

  @Test
  void deleteWishlistTest() {
    DeleteWishlistRequest request = DeleteWishlistRequest.newBuilder().setId(-2).build();
    WishlistResponse actual = stub.deleteWishlist(request);
    assertThat(actual.getErrorsCount()).isZero();
  }

  @Test
  void getWishlistsTest() {
    GetWishlistsRequest request = GetWishlistsRequest.newBuilder().setUsername("test2").build();
    Iterator<WishlistResponse> actual = stub.getWishlists(request);
    List<WishlistResponse> expected =
        List.of(
            WishlistResponse.newBuilder()
                .setItem(WishlistResponseItem.newBuilder().setId(-4).setTitle("wishlist4").build())
                .build(),
            WishlistResponse.newBuilder()
                .setItem(
                    WishlistResponseItem.newBuilder()
                        .setId(-3)
                        .setTitle("wishlist3")
                        .setDescription("description3")
                        .build())
                .build());
    assertThat(actual).toIterable().withRepresentation(representation).isEqualTo(expected);
  }
}
