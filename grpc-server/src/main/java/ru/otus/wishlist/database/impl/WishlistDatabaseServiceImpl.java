package ru.otus.wishlist.database.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.otus.wishlist.database.WishlistDatabaseService;
import ru.otus.wishlist.domain.Wishlist;
import ru.otus.wishlist.repository.WishlistRepository;
import ru.otus.wishlist.transactional.TransactionManager;

@Service
@AllArgsConstructor
public class WishlistDatabaseServiceImpl implements WishlistDatabaseService {

  private final WishlistRepository wishlistRepository;
  private final TransactionManager transactionManager;

  @Override
  public List<Wishlist> findByUserId(Long userId) {
    return wishlistRepository.findByUserId(userId, Sort.by("id"));
  }

  @Override
  public Wishlist saveWishlist(Wishlist value) {
    return transactionManager.doInTransaction(() -> wishlistRepository.save(value));
  }

  @Override
  public Optional<Wishlist> findById(Long id) {
    return wishlistRepository.findById(id);
  }

  @Override
  public void deleteWishlist(Long id) {
    transactionManager.doInTransaction(() -> wishlistRepository.deleteById(id));
  }
}
