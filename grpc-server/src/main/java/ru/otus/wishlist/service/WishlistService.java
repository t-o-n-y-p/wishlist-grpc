package ru.otus.wishlist.service;

import java.util.List;
import ru.otus.protobuf.CreateWishlistRequest;
import ru.otus.protobuf.DeleteWishlistRequest;
import ru.otus.protobuf.GetWishlistsRequest;
import ru.otus.protobuf.UpdateWishlistRequest;
import ru.otus.protobuf.WishlistResponse;

public interface WishlistService {

  WishlistResponse createWishlist(CreateWishlistRequest requestBody);

  List<WishlistResponse> getWishlists(GetWishlistsRequest requestBody);

  WishlistResponse updateWishlist(UpdateWishlistRequest requestBody);

  WishlistResponse deleteWishlist(DeleteWishlistRequest requestBody);
}
