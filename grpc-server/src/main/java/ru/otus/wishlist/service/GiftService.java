package ru.otus.wishlist.service;

import java.util.List;
import ru.otus.protobuf.CreateGiftRequest;
import ru.otus.protobuf.DeleteGiftRequest;
import ru.otus.protobuf.GetGiftsRequest;
import ru.otus.protobuf.GiftResponse;
import ru.otus.protobuf.UpdateGiftRequest;

public interface GiftService {

  GiftResponse createGift(CreateGiftRequest requestBody);

  List<GiftResponse> getGifts(GetGiftsRequest requestBody);

  GiftResponse updateGift(UpdateGiftRequest requestBody);

  GiftResponse deleteGift(DeleteGiftRequest requestBody);
}
