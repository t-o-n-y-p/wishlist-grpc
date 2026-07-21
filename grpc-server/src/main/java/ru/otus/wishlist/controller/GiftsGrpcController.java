package ru.otus.wishlist.controller;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;
import ru.otus.protobuf.CreateGiftRequest;
import ru.otus.protobuf.DeleteGiftRequest;
import ru.otus.protobuf.GetGiftsRequest;
import ru.otus.protobuf.GiftResponse;
import ru.otus.protobuf.GiftsServiceGrpc;
import ru.otus.protobuf.UpdateGiftRequest;
import ru.otus.wishlist.service.GiftService;

@GrpcService
@AllArgsConstructor
public class GiftsGrpcController extends GiftsServiceGrpc.GiftsServiceImplBase {

  private final GiftService giftService;

  @Override
  public void createGift(CreateGiftRequest request, StreamObserver<GiftResponse> responseObserver) {
    responseObserver.onNext(giftService.createGift(request));
    responseObserver.onCompleted();
  }

  @Override
  public void getGifts(GetGiftsRequest request, StreamObserver<GiftResponse> responseObserver) {
    for (GiftResponse gift : giftService.getGifts(request)) {
      responseObserver.onNext(gift);
    }
    responseObserver.onCompleted();
  }

  @Override
  public void updateGift(UpdateGiftRequest request, StreamObserver<GiftResponse> responseObserver) {
    responseObserver.onNext(giftService.updateGift(request));
    responseObserver.onCompleted();
  }

  @Override
  public void deleteGift(DeleteGiftRequest request, StreamObserver<GiftResponse> responseObserver) {
    responseObserver.onNext(giftService.deleteGift(request));
    responseObserver.onCompleted();
  }
}
