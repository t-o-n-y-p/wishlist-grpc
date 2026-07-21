package ru.otus.wishlist.controller;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;
import ru.otus.protobuf.CreateWishlistRequest;
import ru.otus.protobuf.DeleteWishlistRequest;
import ru.otus.protobuf.GetWishlistsRequest;
import ru.otus.protobuf.UpdateWishlistRequest;
import ru.otus.protobuf.WishlistResponse;
import ru.otus.protobuf.WishlistsServiceGrpc;
import ru.otus.wishlist.service.WishlistService;

@GrpcService
@AllArgsConstructor
public class WishlistsGrpcController extends WishlistsServiceGrpc.WishlistsServiceImplBase {

  private final WishlistService wishlistService;

  @Override
  public void createWishlist(
      CreateWishlistRequest request, StreamObserver<WishlistResponse> responseObserver) {
    responseObserver.onNext(wishlistService.createWishlist(request));
    responseObserver.onCompleted();
  }

  @Override
  public void getWishlists(
      GetWishlistsRequest request, StreamObserver<WishlistResponse> responseObserver) {
    for (WishlistResponse wishlistResponse : wishlistService.getWishlists(request)) {
      responseObserver.onNext(wishlistResponse);
    }
    responseObserver.onCompleted();
  }

  @Override
  public void updateWishlist(
      UpdateWishlistRequest request, StreamObserver<WishlistResponse> responseObserver) {
    responseObserver.onNext(wishlistService.updateWishlist(request));
    responseObserver.onCompleted();
  }

  @Override
  public void deleteWishlist(
      DeleteWishlistRequest request, StreamObserver<WishlistResponse> responseObserver) {
    responseObserver.onNext(wishlistService.deleteWishlist(request));
    responseObserver.onCompleted();
  }
}
