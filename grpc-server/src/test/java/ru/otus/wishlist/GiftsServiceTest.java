package ru.otus.wishlist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;
import org.assertj.core.presentation.Representation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.protobuf.CreateGiftRequest;
import ru.otus.protobuf.DeleteGiftRequest;
import ru.otus.protobuf.GetGiftsRequest;
import ru.otus.protobuf.GiftResponse;
import ru.otus.protobuf.GiftResponseItem;
import ru.otus.protobuf.GiftsServiceGrpc;
import ru.otus.protobuf.UpdateGiftRequest;
import ru.otus.wishlist.config.GrpcIntegrationTest;

@GrpcIntegrationTest
public class GiftsServiceTest {

  @Autowired private GiftsServiceGrpc.GiftsServiceBlockingStub stub;
  @Autowired private Representation representation;

  @Test
  void createGiftTest() {
    CreateGiftRequest request =
        CreateGiftRequest.newBuilder().setWishlistId(-3).setTitle("gift").setPrice(10000).build();
    GiftResponse actual = stub.createGift(request);
    GiftResponse expected =
        GiftResponse.newBuilder()
            .setItem(
                GiftResponseItem.newBuilder()
                    .setId(1)
                    .setTitle("gift")
                    .setPrice(10000)
                    .setReserved(false)
                    .build())
            .build();
    assertThat(actual).withRepresentation(representation).isEqualTo(expected);
  }

  @Test
  void updateGiftTest() {
    UpdateGiftRequest request =
        UpdateGiftRequest.newBuilder().setId(-1).setTitle("updated").build();
    GiftResponse actual = stub.updateGift(request);
    GiftResponse expected =
        GiftResponse.newBuilder()
            .setItem(
                GiftResponseItem.newBuilder()
                    .setId(-1)
                    .setTitle("updated")
                    .setPrice(1)
                    .setReserved(true)
                    .build())
            .build();
    assertThat(actual).withRepresentation(representation).isEqualTo(expected);
  }

  @Test
  void deleteGiftTest() {
    DeleteGiftRequest request = DeleteGiftRequest.newBuilder().setId(-2).build();
    GiftResponse actual = stub.deleteGift(request);
    assertThat(actual.getErrorsCount()).isZero();
  }

  @Test
  void getGiftsTest() {
    GetGiftsRequest request = GetGiftsRequest.newBuilder().setWishlistId(-4).build();
    Iterator<GiftResponse> actual = stub.getGifts(request);
    List<GiftResponse> expected =
        List.of(
            GiftResponse.newBuilder()
                .setItem(
                    GiftResponseItem.newBuilder()
                        .setId(-4)
                        .setTitle("gift4")
                        .setPrice(4)
                        .setDescription("description4")
                        .setReserved(false)
                        .build())
                .build(),
            GiftResponse.newBuilder()
                .setItem(
                    GiftResponseItem.newBuilder()
                        .setId(-3)
                        .setTitle("gift3")
                        .setPrice(3)
                        .setReserved(true)
                        .build())
                .build());
    assertThat(actual).toIterable().withRepresentation(representation).isEqualTo(expected);
  }
}
