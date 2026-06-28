package ru.otus.wishlist.database;

import java.util.List;
import java.util.Optional;
import ru.otus.wishlist.domain.Gift;

public interface GiftDatabaseService {

  List<Gift> findByWishlistId(Long wishlistId);

  Gift saveGift(Gift value);

  Optional<Gift> findById(Long id);

  void deleteGift(Long id);
}
