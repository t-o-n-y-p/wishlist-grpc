package ru.otus.wishlist.database;

import java.util.List;
import java.util.Optional;
import ru.otus.wishlist.domain.Wishlist;

public interface WishlistDatabaseService {

  List<Wishlist> findByUserId(Long userId);

  Wishlist saveWishlist(Wishlist value);

  Optional<Wishlist> findById(Long id);

  void deleteWishlist(Long id);
}
